package com.example.ReceitaAPI.Controllers.Company;


import com.example.ReceitaAPI.Dtos.Company.NaturezaLegalDto;
import com.example.ReceitaAPI.Dtos.Company.PorteDaEmpresaDto;
import com.example.ReceitaAPI.Dtos.Company.QualificacaoResponsavelDto;
import com.example.ReceitaAPI.Models.Company.NaturezaLegalModel;
import com.example.ReceitaAPI.Models.Company.PorteDaEmpresaModel;
import com.example.ReceitaAPI.Models.Company.QualificacaoResponsavelModel;
import com.example.ReceitaAPI.Services.Company.CompanyStatusService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/CompanyStatus")
public class CompanyStatusController {
    @Autowired
    CompanyStatusService companyStatusService;

    @PostMapping("/naturezalegal")
    public ResponseEntity<List<NaturezaLegalModel>> addNaturezalegal(@Valid @RequestBody NaturezaLegalDto naturezaLegalDto){
        NaturezaLegalModel obj = new NaturezaLegalModel();
        BeanUtils.copyProperties(naturezaLegalDto,obj);
        var list = companyStatusService.addStatusNaturezaLegal(obj);
        return  ResponseEntity.ok().body(list);
    }

    @PostMapping("/PorteDaEmpres")
    public ResponseEntity<?> addPortedaEmpresa(@Valid @RequestBody PorteDaEmpresaDto porteDaEmpresaDto){
        PorteDaEmpresaModel obj = new PorteDaEmpresaModel();
        BeanUtils.copyProperties(porteDaEmpresaDto,obj);
        var list = companyStatusService.addStatusPorteEmpresa(obj);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/QualificacaoResponsavel")
    public ResponseEntity<?> addQualificacaoResponsavel(@Valid @RequestBody QualificacaoResponsavelDto qualificacaoResponsavelDto){
        QualificacaoResponsavelModel obj = new QualificacaoResponsavelModel();
        BeanUtils.copyProperties(qualificacaoResponsavelDto,obj);
        var list = companyStatusService.addStatusQualificacaoResponsavel(obj);
        return ResponseEntity.ok().body(list);
    }


}
