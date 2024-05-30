package com.example.ReceitaAPI.Controllers.Socio;

import com.example.ReceitaAPI.Services.FileReader.FileReaderSociosService;
import com.example.ReceitaAPI.Services.FileReader.FileService;
import com.example.ReceitaAPI.Services.Socio.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/Socio")
public class SocioController {
    @Autowired
    FileService fileService;
    @Autowired
    SocioService socioService;
    @Autowired
    FileReaderSociosService fileReaderSociosService;
    private AtomicLong taskIdGenerator = new AtomicLong();


    @PostMapping("/addAll")
    public ResponseEntity<?> addAll(){
        String csvPath = "D:\\JavaProjects\\ReceitaAPI\\arquivos\\Socios";

        List<String> files = fileService.listFiles(csvPath);
        long taskId = taskIdGenerator.incrementAndGet();

        for (String str : files) {

            fileReaderSociosService.readCsvFile(str, taskId);
        }

        return ResponseEntity.ok().body("em processamento");
    }

}
