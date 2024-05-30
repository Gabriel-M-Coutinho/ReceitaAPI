


package com.example.ReceitaAPI.Services.FileReader;

import com.example.ReceitaAPI.Models.Company.CompanyModel;
import com.example.ReceitaAPI.Models.Estabelecimento.EstabelecimentoModel;
import com.example.ReceitaAPI.Repositories.Company.CompanyRepository;
import com.example.ReceitaAPI.Repositories.Estabelecimento.EstabelecimentoRepository;
import com.example.ReceitaAPI.Services.Company.CompanyService;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FileReaderCompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    private final ConcurrentHashMap<Long, String> taskStatusMap = new ConcurrentHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(FileReaderBreanchService.class);

    @Async
    public void readCsvFile(String csvFile, Long taskId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        int batchSize = 10000;
        int linecount = 0;
        List<CompanyModel> batchList = new ArrayList<>(batchSize);

        logger.info("Iniciando leitura do arquivo CSV: {}", csvFile);

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            CSVReader reader = new CSVReaderBuilder(br)
                    .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                    .build();
            taskStatusMap.put(taskId, "Processando");



            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                linecount++;


                CompanyModel model = parseLine(nextLine);

                if (model != null) {
                    batchList.add(model);
                }

                if (batchList.size() >= batchSize) {
                    saveBatch(batchList);
                    logger.info("Linhas processadas: {} - Memória utilizada: {} bytes", linecount, Runtime.getRuntime().totalMemory());
                    batchList.clear();
                }
            }

            // Salvar qualquer restante
            if (!batchList.isEmpty()) {
                saveBatch(batchList);
                logger.info("Linhas processadas (restante): {} - Memória utilizada: {} bytes", linecount, Runtime.getRuntime().totalMemory());
            }

            taskStatusMap.put(taskId, "Concluído");
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            taskStatusMap.put(taskId, "Erro: " + e.getMessage());
        } catch (OutOfMemoryError e) {
            logger.error("Erro de memória após processar {} linhas", linecount);
            taskStatusMap.put(taskId, "Erro: Out of Memory");
        }
    }


    private void saveBatch(List<CompanyModel> batchList) {
        companyRepository.saveAll(batchList);
        companyRepository.flush();
        Runtime.getRuntime().gc();
    }



    private CompanyModel parseLine(String[] data) {
        CompanyModel companyModel = new CompanyModel();

        // Verificar se data contém ao menos um elemento e se é válido
        if (data.length > 0 && isValidLong(data[0])) {
            companyModel.setCnpjBaseInt(Long.parseLong(cleanString(data[0])));
            companyModel.setCnpjBaseStr(truncate(cleanString(data[0]), 255));
        }

        // Verificar se data contém ao menos dois elementos
        if (data.length > 1) {
            companyModel.setRazaoSocial(truncate(cleanString(data[1]), 255));
        }

        // Verificar se data contém ao menos três elementos e se é válido
        if (data.length > 2 && isValidLong(data[2])) {
            companyModel.setNaturezaLegal(Long.parseLong(cleanString(data[2])));
        }

        // Verificar se data contém ao menos quatro elementos e se é válido
        if (data.length > 3 && isValidLong(data[3])) {
            companyModel.setQualificacaoResponsavelModel(Long.parseLong(cleanString(data[3])));
        }

        // Verificar se data contém ao menos cinco elementos
        if (data.length > 4) {
            try {
                companyModel.setCapitalSocial(Double.parseDouble(cleanString(data[4])));
            } catch (Exception e) {
                // Log error or handle exception if needed
            }
        }

        // Verificar se data contém ao menos seis elementos e se é válido
        if (data.length > 5 && isValidLong(data[5])) {
            companyModel.setPorteEmpresa(Long.parseLong(cleanString(data[5])));
        }

        // Verificar se data contém ao menos sete elementos
        if (data.length > 6) {
            companyModel.setEnteFederativoResponsavel(truncate(cleanString(data[6]), 255));
        }

        return companyModel;
    }



    private String cleanString(String input) {
        if (input == null) {
            return "";
        }
        // Remove caracteres especiais e espaços
        return input.replaceAll("[^a-zA-Z0-9]", "");
    }

    // Método para truncar a string
    private String truncate(String input, int maxLength) {
        if (input == null) {
            return "";
        }
        return input.length() <= maxLength ? input : input.substring(0, maxLength);
    }

    // Método para verificar se a string pode ser convertida para Long
    private boolean isValidLong(String input) {
        try {
            Long.parseLong(input);
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

    public String getTaskStatus(Long taskId) {
        return taskStatusMap.get(taskId);
    }


}
