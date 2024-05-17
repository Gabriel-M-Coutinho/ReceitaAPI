package com.example.ReceitaAPI.Repositories.Company;

import com.example.ReceitaAPI.Models.Company.PorteDaEmpresaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PorteDaEmpresaRepository extends JpaRepository<PorteDaEmpresaModel, Integer> {
}