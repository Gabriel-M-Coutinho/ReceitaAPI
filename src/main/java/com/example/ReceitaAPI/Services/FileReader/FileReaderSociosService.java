package com.example.ReceitaAPI.Services.FileReader;

import com.example.ReceitaAPI.Models.Company.CompanyModel;
import com.example.ReceitaAPI.Models.Estabelecimento.EstabelecimentoModel;
import com.example.ReceitaAPI.Models.Socios.SocioModel;
import com.example.ReceitaAPI.Repositories.Estabelecimento.EstabelecimentoRepository;
import com.example.ReceitaAPI.Services.Estabelecimento.EstabelecimentoService;
import com.example.ReceitaAPI.Services.Socio.SocioService;
import com.example.ReceitaAPI.Services.UtillsService;
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
public class FileReaderSociosService {

    @Autowired
    private SocioService socioService;
    @Autowired
    private UtillsService utillsService;


    private final ConcurrentHashMap<Long, String> taskStatusMap = new ConcurrentHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(FileReaderBreanchService.class);

    @Async
    public void readCsvFile(String csvFile, Long taskId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        int batchSize = 10000;
        int linecount = 0;
        List<SocioModel> batchList = new ArrayList<>(batchSize);

        logger.info("Iniciando leitura do arquivo CSV: {}", csvFile);

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            CSVReader reader = new CSVReaderBuilder(br)
                    .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                    .build();
            taskStatusMap.put(taskId, "Processando");



            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                linecount++;


                SocioModel model = socioService.parseLine(nextLine, format);

                if (model != null) {
                    batchList.add(model);
                }

                if (batchList.size() >= batchSize) {
                    socioService.saveAll(batchList);
                    logger.info("Linhas processadas: {} - Memória utilizada: {} bytes", linecount, Runtime.getRuntime().totalMemory());
                    batchList.clear();
                }
            }

            // Salvar qualquer restante
            if (!batchList.isEmpty()) {
                socioService.saveAll(batchList);
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



    public String getTaskStatus(Long taskId) {
        return taskStatusMap.get(taskId);
    }


}
