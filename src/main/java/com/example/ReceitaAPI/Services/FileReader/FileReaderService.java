package com.example.ReceitaAPI.Services.FileReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FileReaderService {
    public List<String[]> readCsvFile(String csvFile) {
        List<String[]> dataList = new ArrayList<>();
        int lineCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            String line;

            while ((line = br.readLine()) != null) {
                lineCount++;


                String[] data = line.split(";");

                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replaceAll("^\"|\"$", "").trim();


                }

                dataList.add(data);
                if(lineCount %100 == 0 ){
                    System.out.println(lineCount);
                }

            }
        } catch (IOException e) {
            return dataList;
        }

        return dataList;
    }
}