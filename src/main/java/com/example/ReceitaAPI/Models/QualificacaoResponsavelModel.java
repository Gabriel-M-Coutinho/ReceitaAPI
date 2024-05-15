package com.example.ReceitaAPI.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_Qualificacao_responsavel")
public class QualificacaoResponsavelModel {
    @Id
    private Long id;
    private String description;
}
