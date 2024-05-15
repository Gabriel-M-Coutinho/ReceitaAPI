package com.example.ReceitaAPI.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_Porte_Empresa")
public class PorteDaEmpresaModel {
    @Id
    private Integer id;
    private String description;
}
