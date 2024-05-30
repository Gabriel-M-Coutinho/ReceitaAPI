package com.example.ReceitaAPI.Models.Company;

import com.example.ReceitaAPI.Models.Estabelecimento.EstabelecimentoModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "tb_Companies")

public class CompanyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "cnpj")
    private Long cnpjBaseInt;

    private String cnpjBaseStr;


    private String razaoSocial;


    @Column(name = "NaturezaJuridica")
    private Long naturezaLegal;


    @Column(name = "Qualificacao_Responsavel")
    private Long qualificacaoResponsavelModel;


    private double capitalSocial;


    @Column(name = "PorteEmpresa" )
    private Long porteEmpresa;

    private String enteFederativoResponsavel;




}
