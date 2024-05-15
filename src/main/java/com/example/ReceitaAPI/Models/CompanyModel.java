package com.example.ReceitaAPI.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "tb_Companies")
public class CompanyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(nullable = false)
    private String cnpjBase;
    @Column(nullable = false)
    private String razaoSocial;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LegalNatureId")
    private LegalNatureModel legalNatureModel;

    private String qualificacaoResponsavel;
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
