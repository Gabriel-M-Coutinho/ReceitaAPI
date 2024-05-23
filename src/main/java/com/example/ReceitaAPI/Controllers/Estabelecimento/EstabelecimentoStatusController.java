package com.example.ReceitaAPI.Controllers.Estabelecimento;

import com.example.ReceitaAPI.Dtos.Estabelecimento.IdentificadorMatrizFilialDto;
import com.example.ReceitaAPI.Dtos.Estabelecimento.SituacaoCadastralDto;
import com.example.ReceitaAPI.Services.Estabelecimento.*;
import com.example.ReceitaAPI.Services.FileReader.FileReaderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Estabelecimento")

public class EstabelecimentoStatusController {
    @Autowired
    FileReaderService fileReaderService;
    @Autowired
    EstabelecimentoStatusService estabelecimentoStatusService;
    @Autowired
    CnaeService cnaeService;
    @Autowired
    MunicipioService municipioService;
    @Autowired
    MotivoSituacaoCadastralService motivoSituacaoCadastralService;
    @Autowired
    PaisService paisService;


    /*CNAES*/
    @GetMapping("/addAllCnaes")
    public ResponseEntity<?> addAllCnaes(){
        String csvFile = "D:\\JavaProjects\\ReceitaAPI\\arquivos\\Cnaes\\F.K03200$Z.D40413.CNAECSV";
        List<String[]> data = fileReaderService.readCsvFile(csvFile);
        var obj = cnaeService.trasformData(data);
        cnaeService.addAllCnae(obj);
        return ResponseEntity.ok().body("ok");
    }

    @PostMapping("/MatrizFilial")
    public ResponseEntity<?> addMatrizFilialStatus(@Valid @RequestBody IdentificadorMatrizFilialDto identificadorMatrizFilialDto){
        var status = estabelecimentoStatusService.addFilial(identificadorMatrizFilialDto);
        return ResponseEntity.ok().body(status);
    }

    @PostMapping("/SituacaoCadastral")
    public ResponseEntity<?> addSituacaoCadastral(@Valid @RequestBody SituacaoCadastralDto situacaoCadastralDto){
        var status =   estabelecimentoStatusService.addSituacaoCadastral(situacaoCadastralDto);
        return ResponseEntity.ok().body(status);
    }

    @PostMapping("/Municipios")
    public  ResponseEntity<?> addMunicipio(){
        String csvFile = "D:\\JavaProjects\\ReceitaAPI\\arquivos\\Municipios\\F.K03200$Z.D40413.MUNICCSV";
        List<String[]> data = fileReaderService.readCsvFile(csvFile);
        var response = municipioService.addMunicipio(municipioService.transformInModel(data));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("MotivoSitucao")
    public ResponseEntity<?> addMotivoSituacao(){
        String csvFile = "D:\\JavaProjects\\ReceitaAPI\\arquivos\\Motivos\\F.K03200$Z.D40413.MOTICSV";
        List<String[]> data = fileReaderService.readCsvFile(csvFile);
        var listModel = motivoSituacaoCadastralService.transformInModel(data);
        var listResponse = motivoSituacaoCadastralService.addSituaCaocadastral(listModel);
        return ResponseEntity.ok().body(listResponse);
    }

    @PostMapping("Pais")
    public  ResponseEntity<?> addPais(){
        String csvFile = "D:\\JavaProjects\\ReceitaAPI\\arquivos\\Paises\\F.K03200$Z.D40413.PAISCSV";
        List<String[]> data = fileReaderService.readCsvFile(csvFile);
        var listModel = paisService.trasformData(data);
        paisService.addAllContry(listModel);
        return ResponseEntity.ok().body("ok");
    }



}
