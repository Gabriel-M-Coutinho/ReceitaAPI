package com.example.ReceitaAPI.Models.Company;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "tb_Qualificacao_responsavel")
public class QualificacaoResponsavelModel {
    @Id
    private Integer id;
    private String description;


}
