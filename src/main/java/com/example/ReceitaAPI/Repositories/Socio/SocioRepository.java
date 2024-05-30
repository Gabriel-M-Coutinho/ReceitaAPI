package com.example.ReceitaAPI.Repositories.Socio;

import com.example.ReceitaAPI.Models.Socios.SocioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepository extends JpaRepository<SocioModel, Long> {
}