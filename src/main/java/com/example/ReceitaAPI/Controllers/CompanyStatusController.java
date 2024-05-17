package com.example.ReceitaAPI.Controllers;

import com.example.ReceitaAPI.Dtos.Estabelecimento.IdentificadorMatrizFilialDto;
import com.example.ReceitaAPI.Models.Estabelecimento.IdentificadorMatrizFilialModel;
import com.example.ReceitaAPI.Services.CompanyStatusService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyStatusController {

    @Autowired
    CompanyStatusService companyStatusService;
    @PostMapping("/identificadorMatrizFilial")
    public ResponseEntity<?> addMatrizFilialStatus(@Valid @RequestBody IdentificadorMatrizFilialDto identificadorMatrizFilialDto){
        IdentificadorMatrizFilialModel identificadorMatrizFilialModel = new IdentificadorMatrizFilialModel();
        BeanUtils.copyProperties(identificadorMatrizFilialDto,identificadorMatrizFilialModel);
        return ResponseEntity.ok().body(companyStatusService.addIdentificadorMatrizFilial(identificadorMatrizFilialModel));

    }
}
