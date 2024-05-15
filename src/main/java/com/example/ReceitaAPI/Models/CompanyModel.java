package com.example.ReceitaAPI.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "tb_Companies")
public class CompanyModel {

    @Id
    @Column(nullable = false)
    private String cnpjBase;
    @Column(nullable = false)
    private String razaoSocial;

    @ManyToOne
    @JoinColumn(name = "LegalNatureId",referencedColumnName = "id")
    private LegalNatureModel legalNatureModel;

    @ManyToMany
    @JoinColumn(name = "Qualificacao_Responsavel_id",referencedColumnName = "id")
    private QualificacaoResponsavelModel qualificacaoResponsavelModel;

    @Column(nullable = false)
    private double capitalSocial;
    @Column(nullable = false)
    private String porteEmpresa;
    @Column(nullable = false)
    private String enteFederativoResponsavel;
    

    //@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    //private List<UserModel> employeelist;



    public CompanyModel(String name, String cnpj) {
 
    }







}
