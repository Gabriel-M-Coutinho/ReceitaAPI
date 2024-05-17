package com.example.ReceitaAPI.Models.Estabelecimento;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_municipio")
public class MunicipioModel {
    @Id
    private int id;
    private String description;
}
