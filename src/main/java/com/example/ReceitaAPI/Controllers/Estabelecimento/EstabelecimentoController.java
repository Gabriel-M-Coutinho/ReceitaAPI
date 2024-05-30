package com.example.ReceitaAPI.Controllers.Estabelecimento;

import com.example.ReceitaAPI.Services.FileReader.FileReaderBreanchService;
import com.example.ReceitaAPI.Services.FileReader.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class EstabelecimentoController {

    @Autowired
    private FileService fileService;

    @Autowired
    private FileReaderBreanchService fileReaderBreanchService;

    private AtomicLong taskIdGenerator = new AtomicLong();

    @PostMapping("/Estabelecimentos/")
    public ResponseEntity<?> addEstabelecimento() {
        String path = "D:\\JavaProjects\\ReceitaAPI\\arquivos\\Estabelecimento\\estabelecimentoscsv";
        List<String> directories = fileService.listFiles(path);

        long taskId = taskIdGenerator.incrementAndGet();
        for (String str : directories) {

            fileReaderBreanchService.readCsvFile(str, taskId);
        }

        return ResponseEntity.ok().body("Tarefa iniciada com ID: " + taskId);
    }




    @GetMapping("/Estabelecimentos/status/{taskId}")
    public ResponseEntity<String> getTaskStatus(@PathVariable Long taskId) {
        String status = fileReaderBreanchService.getTaskStatus(taskId);
        if (status != null) {
            return ResponseEntity.ok(status);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
