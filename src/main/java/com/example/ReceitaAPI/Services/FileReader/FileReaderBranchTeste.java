package com.example.ReceitaAPI.Services.FileReader;

import com.example.ReceitaAPI.Models.Estabelecimento.EstabelecimentoModel;
import com.example.ReceitaAPI.Repositories.Estabelecimento.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileReaderBranchTeste {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public void readCsvFile(String csvFile) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        int batchSize = 10000; // Define o tamanho do lote
        StringBuilder sql = new StringBuilder("INSERT INTO tb_estabelecimento VALUES");
        int batchCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                EstabelecimentoModel model = parseLine(line, format);
                if (model != null) {
                    addValuesToBatch(model, sql);
                    batchCount++;

                    if (batchCount == batchSize) {
                        executeBatch(sql.toString());
                        sql.setLength(0);
                        sql.append("INSERT INTO tb_estabelecimento VALUES");
                        batchCount = 0;
                    }
                }
            }

            // Executa qualquer lote remanescente
            if (batchCount > 0) {
                executeBatch(sql.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addValuesToBatch(EstabelecimentoModel model, StringBuilder sql) {
        sql.append("(")
                .append("'").append(model.getCnpjFull()).append("', ")
                .append("'").append(model.getCnpjBaseId()).append("', ")
                .append("'").append(model.getCnpjOrdem()).append("', ")
                .append("'").append(model.getCnpjDV()).append("', ")
                .append("'").append(model.getIdentificadorMatrizFilialId()).append("', ")
                .append("'").append(model.getNomeFantasia()).append("', ")
                .append("'").append(model.getSitutacaoCadastralId()).append("', ")
                .append("'").append(formatDate(model.getDataSituacaoCadastral())).append("', ")
                .append("'").append(model.getMotivoSituacaoCadastralId()).append("', ")
                .append("'").append(model.getNomeCidadeExterior()).append("', ")
                .append("'").append(model.getPaisId()).append("', ")
                .append("'").append(formatDate(model.getDataInicioAtividade())).append("', ")
                .append("'").append(model.getCnaeFiscalPrincipalId()).append("', ")
                .append("'").append(model.getCnaeFiscalSecundaria()).append("', ")
                .append("'").append(model.getTipoLogradouro()).append("', ")
                .append("'").append(model.getLogradouro()).append("', ")
                .append("'").append(model.getNumero()).append("', ")
                .append("'").append(model.getComplemento()).append("', ")
                .append("'").append(model.getBairro()).append("', ")
                .append("'").append(model.getCep()).append("', ")
                .append("'").append(model.getUf()).append("', ")
                .append("'").append(model.getMunicipioId()).append("', ")
                .append("'").append(model.getDdd1()).append("', ")
                .append("'").append(model.getTelefone1()).append("', ")
                .append("'").append(model.getDdd2()).append("', ")
                .append("'").append(model.getTelefone2()).append("', ")
                .append("'").append(model.getDddfax()).append("', ")
                .append("'").append(model.getFax()).append("', ")
                .append("'").append(model.getEmail()).append("', ")
                .append("'").append(model.getSituacaoespecial()).append("', ")
                .append("'").append(formatDate(model.getDatasituacaoespecial())).append("'), ");
    }

    private void executeBatch(String sql) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_homologacao", "root", "Rock0603@desert!");
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private EstabelecimentoModel parseLine(String line, SimpleDateFormat format) {
        String[] data = line.split(";");
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replaceAll("^\"|\"$", "").trim();
        }

        Date dataSituacaoCadastral = parseDate(data[6], format);
        Date dataInicioAtividades = parseDate(data[10], format);
        Date dateSituacaoEspecial = parseDate(data[29], format);

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
    private String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}

