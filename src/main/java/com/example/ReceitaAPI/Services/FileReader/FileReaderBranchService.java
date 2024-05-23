package com.example.ReceitaAPI.Services.FileReader;

import com.example.ReceitaAPI.Models.Estabelecimento.EstabelecimentoModel;
import com.example.ReceitaAPI.Models.Estabelecimento.IdentificadorMatrizFilialModel;
import com.example.ReceitaAPI.Models.Estabelecimento.MotivoSituacaoCadastralModel;
import com.example.ReceitaAPI.Models.Estabelecimento.SitutacaoCadastralModel;
import com.example.ReceitaAPI.Repositories.Estabelecimento.EstabelecimentoRepository;
import com.example.ReceitaAPI.Repositories.Estabelecimento.IdentificadorMatrizFilialRepository;
import com.example.ReceitaAPI.Repositories.Estabelecimento.MotivoSituacaoCadastralRepository;
import com.example.ReceitaAPI.Repositories.Estabelecimento.SituacaoCadastralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileReaderBranchService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public void readCsvFile(String csvFile) {
        List<EstabelecimentoModel> estabelecimentoModels = new ArrayList<>();
        int lineCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                lineCount++;

                String[] data = line.split(";");
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replaceAll("^\"|\"$", "").trim();
                }

                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

                Date dataSituacaoCadastral = null;
                try {
                    dataSituacaoCadastral = format.parse(data[5]);
                } catch (ParseException e) {
                    // Lidar com exceção
                }

                Date dataInicioAtividades = null;
                try {
                    dataInicioAtividades = format.parse(data[8]);
                } catch (ParseException e) {
                    // Lidar com exceção
                }

                EstabelecimentoModel estabelecimentoModel = new EstabelecimentoModel();
                estabelecimentoModel.setCnpjBaseId(data[0]);
                estabelecimentoModel.setCnpjOrdem(data[1]);
                estabelecimentoModel.setCnpjDV(data[2]);

                if (!data[3].isEmpty() && isNumeric(data[3])) {
                    estabelecimentoModel.setIdentificadorMatrizFilialId(Long.parseLong(data[3]));
                }
                if(!data[4].isEmpty()){
                    estabelecimentoModel.setNomeFantasia(data[4]);
                }


                if (!data[5].isEmpty() && isNumeric(data[5])) {
                    estabelecimentoModel.setSitutacaoCadastralId(Long.parseLong(data[5]));
                }

                if(!data[6].isEmpty()){
                    estabelecimentoModel.setNomeCidadeExterior(data[6]);
                }


                if (!data[7].isEmpty() && isNumeric(data[7])) {
                    estabelecimentoModel.setPaisId(Long.parseLong(data[7]));
                }

                estabelecimentoModel.setDataInicioAtividade(dataInicioAtividades);

                if (!data[9].isEmpty() && isNumeric(data[9])) {
                    estabelecimentoModel.setCnaeFiscalPrincipalId(Long.parseLong(data[9]));
                }

                estabelecimentoModel.setCnaeFiscalSecundaria(data[10]);
                estabelecimentoModel.setTipoLogradouro(data[11]);
                if(!data[12].isEmpty() && data[12].length() <= 255){
                    estabelecimentoModel.setNumero(data[12]);
                }

                estabelecimentoModel.setComplemento(data[13]);
                estabelecimentoModel.setBairro(data[14]);
                estabelecimentoModel.setCep(data[15]);
                estabelecimentoModel.setUf(data[16]);

                if (!data[17].isEmpty() && isNumeric(data[17])) {
                    estabelecimentoModel.setMunicipioId(Long.parseLong(data[17]));
                }

                estabelecimentoModels.add(estabelecimentoModel);

                if (estabelecimentoModels.size() == 10000) {
                    estabelecimentoRepository.saveAll(estabelecimentoModels);
                    estabelecimentoModels.clear();
                    System.out.println("Pegado 10mil e colocado no banco");
                }

                if (lineCount % 100 == 0) {
                    System.out.println(lineCount);
                }
            }

            // Salva o restante dos registros
            if (!estabelecimentoModels.isEmpty()) {
                estabelecimentoRepository.saveAll(estabelecimentoModels);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}



