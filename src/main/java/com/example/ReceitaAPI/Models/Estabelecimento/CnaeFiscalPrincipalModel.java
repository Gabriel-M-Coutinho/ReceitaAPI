package com.example.ReceitaAPI.Models.Estabelecimento;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_cnae")
public class CnaeFiscalPrincipalModel {

    @Id
    private int id;
    private String description;

    public CnaeFiscalPrincipalModel() {
    }

    public CnaeFiscalPrincipalModel(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
