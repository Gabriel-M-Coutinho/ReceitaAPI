package com.example.ReceitaAPI.Services.FileReader;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileService {

    public List<String> listFiles(String directory) {
        List<String> filePaths = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(directory))) {
            filePaths = paths
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePaths;
    }




}