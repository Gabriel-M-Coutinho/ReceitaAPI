package com.example.ReceitaAPI.Controllers;

import com.example.ReceitaAPI.Dtos.Company.CompanyDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class CompanyController {



    @PostMapping("/")
    public void register (@Valid @RequestBody CompanyDto companyDto){
       // CompanyModel company = companyService.createCompany(companyDto);
       // return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }


    @GetMapping("/arena")
    public ResponseEntity<String> arena(){
        return ResponseEntity.ok().body("teste123");
    }


}
