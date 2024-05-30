package com.example.ReceitaAPI.Repositories.Company;

import com.example.ReceitaAPI.Models.Company.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyModel, Long> {

}