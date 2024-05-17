package com.example.ReceitaAPI.Models.Estabelecimento;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tb_identificador_Matriz_filial")
public class IdentificadorMatrizFilialModel {
    @Id
    private Integer id;

    private String description;
}












