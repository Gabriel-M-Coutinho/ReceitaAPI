package com.example.ReceitaAPI.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "tb_Branches")
public class BranchModel {

    private String cnpjBase;
    private String cnpjOrdem;
    private String cnpjDV;
    private int identificadorMatrizFilial;
    private String nomeFantasia;
    private String situacaoCadastral;
    private Date dataSituacaoCadastral;
    private int motivoSituacaoCadastral;
    private String nomeCidadeExterior;
    private String pais;
    private Date dataInicioAtividade;
    private String cnaeFiscalPrincipal;
    private String cnaeFiscalSecundaria;
    private String tipoLogradouro;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String uf;
    private int municipio;
}
