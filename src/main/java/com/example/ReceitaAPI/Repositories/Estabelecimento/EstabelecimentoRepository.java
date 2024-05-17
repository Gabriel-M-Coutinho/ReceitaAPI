package com.example.ReceitaAPI.Repositories.Estabelecimento;

import com.example.ReceitaAPI.Models.Estabelecimento.EstabelecimentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstabelecimentoRepository extends JpaRepository<EstabelecimentoModel, Long> {
}