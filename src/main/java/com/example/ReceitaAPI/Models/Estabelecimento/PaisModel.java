package com.example.ReceitaAPI.Models.Estabelecimento;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_pais")
public class PaisModel {
    @Id
    int id;
    String description;

    public PaisModel() {
    }

    public PaisModel(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
