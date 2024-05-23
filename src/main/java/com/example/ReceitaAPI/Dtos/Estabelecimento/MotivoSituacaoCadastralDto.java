package com.example.ReceitaAPI.Dtos.Estabelecimento;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_MotivoSituacaoCadastral")
public class MotivoSituacaoCadastralDto {
    @Id
    private int id;
    private String description;
}
