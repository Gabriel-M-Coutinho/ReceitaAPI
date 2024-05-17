package com.example.ReceitaAPI.Models.Estabelecimento;

import com.example.ReceitaAPI.Models.Company.CompanyModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "tb_Branches")
public class EstabelecimentoModel {
    @Id
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "cnpj_base",referencedColumnName = "cnpjBase")
    private CompanyModel cnpjBase;

    private String cnpjOrdem;
    private String cnpjDV;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdentificadorMatrizFilial_Id",referencedColumnName = "id")
    private IdentificadorMatrizFilialModel identificadorMatrizFilialModel;

    private String nomeFantasia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SituacaoCadastral_id",referencedColumnName = "id")
    private SitutacaoCadastralModel situtacaoCadastralModel;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Municipio_id",referencedColumnName = "id")
    private MunicipioModel municipioModel;
}
