package com.example.ReceitaAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_identificador_Matriz_filial")
public class IdentificadorMatrizFilialModel {
    @Id
    private Integer id;

    @JsonIgnore
    @ManyToOne()
    private BranchModel branchModel;
    private String description;
}
