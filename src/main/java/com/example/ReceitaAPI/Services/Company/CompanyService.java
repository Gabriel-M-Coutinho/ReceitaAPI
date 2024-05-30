package com.example.ReceitaAPI.Services.Company;

import com.example.ReceitaAPI.Models.Company.CompanyModel;
import com.example.ReceitaAPI.Models.Estabelecimento.EstabelecimentoModel;
import com.example.ReceitaAPI.Repositories.Company.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyModelRepository;

    public Optional<CompanyModel> findUser(Long cnpj_base){
      return companyModelRepository.findById(cnpj_base);
    }

    public void deleteUser(Long cnpj_base){
        companyModelRepository.deleteById(cnpj_base);

    }

    /*public CompanyModel addEstabelecimento(EstabelecimentoModel estabelecimentoModel){
        Optional<CompanyModel> existingCompany =  companyModelRepository.findById(estabelecimentoModel.getCnpjBaseId());
        existingCompany.get().AddEstabeleciomento(estabelecimentoModel);
        return companyModelRepository.save(existingCompany.get());
    }*/

    public CompanyModel addCompany(CompanyModel companyModel){
        Optional<CompanyModel> existingCompany =  companyModelRepository.findById(companyModel.getId());
        return companyModelRepository.save(existingCompany.get());
    }



}
