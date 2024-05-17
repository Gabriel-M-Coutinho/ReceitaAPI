package com.example.ReceitaAPI.Services;

import com.example.ReceitaAPI.Models.Company.CompanyModel;
import com.example.ReceitaAPI.Repositories.Company.CompanyModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyModelRepository companyModelRepository;

    public Optional<CompanyModel> findUser(String cnpj_base){
      return companyModelRepository.findById(cnpj_base);
    }

    public void deleteUser(String cnpj_base){
        companyModelRepository.deleteById(cnpj_base);

    }

}
