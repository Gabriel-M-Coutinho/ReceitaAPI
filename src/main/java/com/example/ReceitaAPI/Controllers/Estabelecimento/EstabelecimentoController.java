package com.example.ReceitaAPI.Controllers.Estabelecimento;

import com.example.ReceitaAPI.Services.FileReader.FileReaderBranchService;
import com.example.ReceitaAPI.Services.FileReader.FileReaderService;
import com.example.ReceitaAPI.Services.FileReader.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EstabelecimentoController {
    @Autowired
    FileService fileService;
    @Autowired
    FileReaderService fileReaderService;

    @Autowired
    FileReaderBranchService fileReaderBranchService;


    @PostMapping("Estabelecimentos")
    public ResponseEntity<?> teste(){
        String path = "D:\\JavaProjects\\ReceitaAPI\\arquivos\\Estabelecimento";
        List<String> diretorios =  fileService.listFiles(path);
        diretorios.forEach((str)->{
            fileReaderBranchService.readCsvFile(str);
        });

        return ResponseEntity.ok().body("ok");
    }

}
