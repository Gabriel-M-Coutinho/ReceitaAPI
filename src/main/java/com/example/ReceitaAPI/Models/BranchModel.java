package com.example.ReceitaAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "tb_Branches")
public class BranchModel {
    @Id
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cnpj_base",referencedColumnName = "cnpjBase")
    private CompanyModel cnpjBase;

    @JsonIgnore
    @ManyToOne
    private CompanyModel companyModel;
    private String cnpjOrdem;
    private String cnpjDV;
    @ManyToOne
    private IdentificadorMatrizFilialModel identificadorMatrizFilialModel;
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
