package com.example.ReceitaAPI.Models.Estabelecimento;

import com.example.ReceitaAPI.Models.Company.CompanyModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.Date;


@Data
@Entity
@Table(name = "tb_Estabelecimentos")
public class EstabelecimentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cnpj_base")
    private String cnpjBaseId;

    private String cnpjOrdem;
    private String cnpjDV;

    @Column(name = "IdentificadorMatrizFilial_Id")
    private Long identificadorMatrizFilialId; // Alterado para Long

    private String nomeFantasia;

    @Column(name = "SituacaoCadastral_id")
    private Long situtacaoCadastralId; // Alterado para Long

    private Date dataSituacaoCadastral;

    @Column(name = "motivoSituacaoCadastral_id")
    private Long motivoSituacaoCadastralId; // Alterado para Long

    private String nomeCidadeExterior;

    @Column(name = "pais_id")
    private Long paisId; // Alterado para Long

    private Date dataInicioAtividade;

    @Column(name = "cnaePrincipal_id")
    private Long cnaeFiscalPrincipalId; // Alterado para Long

    private String cnaeFiscalSecundaria;
    private String tipoLogradouro;
    private String logradouro;
    @Column(name = "numero", length = 255)
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String uf;

    @Column(name = "Municipio_id")
    private Long municipioId; // Alterado para Long

    public EstabelecimentoModel() {
    }

    public EstabelecimentoModel(String cnpjBaseId, String cnpjOrdem, String cnpjDV, Long identificadorMatrizFilialId, String nomeFantasia, Long situtacaoCadastralId, Date dataSituacaoCadastral, Long motivoSituacaoCadastralId, String nomeCidadeExterior, Long paisId, Date dataInicioAtividade, Long cnaeFiscalPrincipalId, String cnaeFiscalSecundaria, String tipoLogradouro, String logradouro, String numero, String complemento, String bairro, String cep, String uf, Long municipioId) {
        this.cnpjBaseId = cnpjBaseId;
        this.cnpjOrdem = cnpjOrdem;
        this.cnpjDV = cnpjDV;
        this.identificadorMatrizFilialId = identificadorMatrizFilialId;
        this.nomeFantasia = nomeFantasia;
        this.situtacaoCadastralId = situtacaoCadastralId;
        this.dataSituacaoCadastral = dataSituacaoCadastral;
        this.motivoSituacaoCadastralId = motivoSituacaoCadastralId;
        this.nomeCidadeExterior = nomeCidadeExterior;
        this.paisId = paisId;
        this.dataInicioAtividade = dataInicioAtividade;
        this.cnaeFiscalPrincipalId = cnaeFiscalPrincipalId;
        this.cnaeFiscalSecundaria = cnaeFiscalSecundaria;
        this.tipoLogradouro = tipoLogradouro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.uf = uf;
        this.municipioId = municipioId;
    }
}
