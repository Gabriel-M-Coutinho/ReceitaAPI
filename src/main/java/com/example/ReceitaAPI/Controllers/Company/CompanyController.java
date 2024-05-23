package com.example.ReceitaAPI.Controllers.Company;

import com.example.ReceitaAPI.Dtos.Company.CompanyDto;
import com.example.ReceitaAPI.Dtos.Estabelecimento.CnaePrincipalDto;
import com.example.ReceitaAPI.Models.Company.CompanyModel;
import com.example.ReceitaAPI.Models.Estabelecimento.CnaeFiscalPrincipalModel;
import com.example.ReceitaAPI.Models.Estabelecimento.EstabelecimentoModel;
import com.example.ReceitaAPI.Services.Company.CompanyService;
import com.example.ReceitaAPI.Services.Estabelecimento.EstabelecimentoStatusService;
import com.example.ReceitaAPI.Services.FileReader.FileReaderService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("company/")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @Autowired
    FileReaderService fileReaderService;
    @Autowired
    EstabelecimentoStatusService estabelecimentoStatusService;



    @PostMapping("/add")
    public ResponseEntity<?> AddCompany (@Valid @RequestBody CompanyDto companyDto){
        CompanyModel obj = new CompanyModel();
        BeanUtils.copyProperties(companyDto,obj);
        var response = companyService.addCompany(obj);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/arena")
    public ResponseEntity<String> arena(){
        return ResponseEntity.ok().body("teste123");
    }


  /*  @PostMapping("/addEstabelecimento")
    public ResponseEntity<EstabelecimentoModel> addEstabelecimento(@Valid @RequestBody EstabelecimentoDto){

    }*/
}
