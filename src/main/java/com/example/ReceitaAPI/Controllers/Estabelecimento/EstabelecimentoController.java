package com.example.ReceitaAPI.Controllers.Estabelecimento;

import com.example.ReceitaAPI.Services.FileReader.FileReaderBranchTeste;

import com.example.ReceitaAPI.Services.FileReader.FileReaderBreanchService;
import com.example.ReceitaAPI.Services.FileReader.FileReaderService;
import com.example.ReceitaAPI.Services.FileReader.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class EstabelecimentoController {
    @Autowired
    FileService fileService;
    @Autowired
    FileReaderService fileReaderService;

    @Autowired
    FileReaderBreanchService fileReaderBreanchService;


    @PostMapping("Estabelecimentos/")
    public ResponseEntity<?> addEstabelecimento(@PathVariable String id, @RequestParam("NumeroDaPasta") String NumeroDaPasta){
        String path = "D:\\JavaProjects\\ReceitaAPI\\arquivos\\Estabelecimento\\estabelecimentoparts\\K3241.K03200Y"+NumeroDaPasta+".D40413.ESTABELE";
        List<String> diretorios =  fileService.listFiles(path);

        /* voltar a partir do 5 */
        diretorios.forEach(str -> {
            if(str.contains("primeiras_linhas.csv") ){
                System.out.println("iniciado o: " + str);

                fileReaderBreanchService.readCsvFile(str);
            }

            System.out.println("terminou");

        });



        return ResponseEntity.ok().body("ok");
    }




}
