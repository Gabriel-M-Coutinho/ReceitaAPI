package com.example.ReceitaAPI.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class QualificacaoResponsavelModel {
    @Id
    private Long id;
    private String description;
}
