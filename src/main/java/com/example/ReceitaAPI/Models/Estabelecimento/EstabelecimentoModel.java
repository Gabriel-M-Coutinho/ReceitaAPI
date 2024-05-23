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

    private String cnpjFull;

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

    @Lob
    @Column(name = "cnaeFiscalSecundaria", columnDefinition = "LONGTEXT")
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


    private String ddd1;
    private String telefone1;
    private String ddd2;
    private String telefone2;
    private String dddfax;
    private String fax;
    private String email;
    private String situacaoespecial;
    private Date datasituacaoespecial;


    public EstabelecimentoModel() {
    }


}
