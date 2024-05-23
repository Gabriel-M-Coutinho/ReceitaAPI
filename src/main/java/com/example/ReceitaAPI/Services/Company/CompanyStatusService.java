package com.example.ReceitaAPI.Services.Company;

import com.example.ReceitaAPI.Models.Company.NaturezaLegalModel;
import com.example.ReceitaAPI.Models.Company.PorteDaEmpresaModel;
import com.example.ReceitaAPI.Models.Company.QualificacaoResponsavelModel;
import com.example.ReceitaAPI.Models.Estabelecimento.IdentificadorMatrizFilialModel;
import com.example.ReceitaAPI.Repositories.Company.NaturezaLegalRepository;
import com.example.ReceitaAPI.Repositories.Company.PorteDaEmpresaRepository;
import com.example.ReceitaAPI.Repositories.Company.QualificacaoResponsavelRepository;
import com.example.ReceitaAPI.Repositories.Estabelecimento.IdentificadorMatrizFilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CompanyStatusService {

    @Autowired
    NaturezaLegalRepository naturezaLegalRepository;
    @Autowired
    PorteDaEmpresaRepository porteDaEmpresaRepository;
    @Autowired
    QualificacaoResponsavelRepository qualificacaoResponsavelRepository;


    public List<NaturezaLegalModel> addStatusNaturezaLegal(NaturezaLegalModel naturezaLegalModel){

        Optional<NaturezaLegalModel> obj = naturezaLegalRepository.findById(naturezaLegalModel.getId());
        if(obj.isEmpty()){
            naturezaLegalRepository.save(naturezaLegalModel);
            return naturezaLegalRepository.findAll();
        }

        throw new Error("status de Natureza Legal ja cadastrado");
    }
    public List<PorteDaEmpresaModel> addStatusPorteEmpresa(PorteDaEmpresaModel porteDaEmpresaModel){
        Optional<PorteDaEmpresaModel> obj = porteDaEmpresaRepository.findById(porteDaEmpresaModel.getId());

        if(obj.isEmpty()){
            porteDaEmpresaRepository.save(porteDaEmpresaModel);
            return porteDaEmpresaRepository.findAll();
        }

        throw new Error("status de Porte da Empresa legal já cadastrado");
    }

    public List<QualificacaoResponsavelModel> addStatusQualificacaoResponsavel(QualificacaoResponsavelModel qualificacaoResponsavelModel){
        Optional<QualificacaoResponsavelModel> obj = qualificacaoResponsavelRepository.findById(qualificacaoResponsavelModel.getId());
        if(obj.isEmpty()){
            qualificacaoResponsavelRepository.save(qualificacaoResponsavelModel);
            return qualificacaoResponsavelRepository.findAll();
        }
        throw new Error("status de qualificacao responsavel ja cadastrada");
    }









}
