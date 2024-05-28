package com.example.ReceitaAPI.Services.FileReader;
import com.example.ReceitaAPI.Models.Estabelecimento.EstabelecimentoModel;
import com.example.ReceitaAPI.Repositories.Estabelecimento.EstabelecimentoRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileReaderBreanchService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;


    public void readCsvFile(String csvFile) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        int batchSize = 10000;  // Ajuste o tamanho do lote conforme necessário
        int linecount = 0;
        List<EstabelecimentoModel> batchList = new ArrayList<>(batchSize);


        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {

                linecount++;
                EstabelecimentoModel model = parseLine(nextLine, format);

                if (model != null) {
                    batchList.add(model);
                }

                if (batchList.size() >= batchSize) {
                    estabelecimentoRepository.saveAll(batchList);
                    estabelecimentoRepository.flush();
                    batchList.clear();
                    System.out.println("Processed lines: " + linecount + "\n" + "heap memory: " + Runtime.getRuntime().totalMemory());
                }
            }

            // Restante do código...
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            System.out.println(linecount);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    private EstabelecimentoModel parseLine(String[] data, SimpleDateFormat format) {
        Date dataSituacaoCadastral = parseDate(data[6], format);
        Date dataInicioAtividades = parseDate(data[10], format);

        Date dateSituacaoEspecial =null;

        try{
            if(data[29] != null){
                if(data.length > 28){
                    dateSituacaoEspecial = parseDate(data[29], format);
                }

            }
        } catch (Exception e) {

        }


        EstabelecimentoModel estabelecimentoModel = new EstabelecimentoModel();
        estabelecimentoModel.setCnpjFull(data[0] + data[1] + data[2]);
        estabelecimentoModel.setCnpjBaseId(data[0]);
        estabelecimentoModel.setCnpjOrdem(data[1]);
        estabelecimentoModel.setCnpjDV(data[2]);

        if (isValidLong(data[3])) {
            estabelecimentoModel.setIdentificadorMatrizFilialId(Long.parseLong(data[3]));
        }
        if (!data[4].isEmpty()) {
            estabelecimentoModel.setNomeFantasia(data[4]);
        }

        if (isValidLong(data[5])) {
            estabelecimentoModel.setSitutacaoCadastralId(Long.parseLong(data[5]));
        }

        if (dataSituacaoCadastral != null && isDateValid(dataSituacaoCadastral)) {
            estabelecimentoModel.setDataSituacaoCadastral(dataSituacaoCadastral);
        }

        if (isValidLong(data[7])) {
            estabelecimentoModel.setMotivoSituacaoCadastralId(Long.parseLong(data[7]));
        }

        estabelecimentoModel.setNomeCidadeExterior(data[8]);

        if (isValidLong(data[9])) {
            estabelecimentoModel.setPaisId(Long.parseLong(data[9]));
        }

        if (dataInicioAtividades != null && isDateValid(dataInicioAtividades)) {
            estabelecimentoModel.setDataInicioAtividade(dataInicioAtividades);
        }

        if (isValidLong(data[11])) {
            estabelecimentoModel.setCnaeFiscalPrincipalId(Long.parseLong(data[11]));
        }

        estabelecimentoModel.setCnaeFiscalSecundaria(data[12]);
        estabelecimentoModel.setTipoLogradouro(data[13]);
        estabelecimentoModel.setLogradouro(data[14]);
        estabelecimentoModel.setNumero(data[15]);
        estabelecimentoModel.setComplemento(data[16]);
        estabelecimentoModel.setBairro(data[17]);
        estabelecimentoModel.setCep(data[18]);
        estabelecimentoModel.setUf(data[19]);

        if (isValidLong(data[20])) {
            estabelecimentoModel.setMunicipioId(Long.parseLong(data[20]));
        }

        estabelecimentoModel.setDdd1(data[21]);
        estabelecimentoModel.setTelefone1(data[22]);
        estabelecimentoModel.setDdd2(data[23]);
        estabelecimentoModel.setTelefone2(data[24]);
        estabelecimentoModel.setDddfax(data[25]);
        estabelecimentoModel.setFax(data[26]);
        estabelecimentoModel.setEmail(data[27]);
        estabelecimentoModel.setSituacaoespecial(data[28]);

        if (dateSituacaoEspecial != null && isDateValid(dateSituacaoEspecial)) {
            estabelecimentoModel.setDatasituacaoespecial(dateSituacaoEspecial);
        }

        return estabelecimentoModel;
    }

    private Date parseDate(String dateString, SimpleDateFormat format) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return null;
        }
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    private boolean isValidLong(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDateValid(Date date) {
        if (date == null) {
            return true; // Considera datas vazias como válidas
        }
        Calendar cal = Calendar.getInstance();
        cal.set(1900, Calendar.JANUARY, 1); // Data mínima aceitável: 1º de janeiro de 1900
        Date minDate = cal.getTime();

        cal.set(2100, Calendar.DECEMBER, 31); // Data máxima aceitável: 31 de dezembro de 2100
        Date maxDate = cal.getTime();

        return date.after(minDate) && date.before(maxDate);
    }
}
