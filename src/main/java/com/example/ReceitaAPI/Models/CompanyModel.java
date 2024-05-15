package com.example.ReceitaAPI.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
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

    @ManyToMany
    @JoinColumn(name = "PorteEmpresaId" , referencedColumnName = "id")
    private String porteEmpresa;

    private String enteFederativoResponsavel;

    @OneToMany(mappedBy = "cnpjBase",cascade = CascadeType.ALL)
    List<BranchModel> Estabelecimentos;


    public CompanyModel(String cnpjBase, String razaoSocial, LegalNatureModel legalNatureModel, QualificacaoResponsavelModel qualificacaoResponsavelModel, double capitalSocial, String porteEmpresa, String enteFederativoResponsavel) {
        this.cnpjBase = cnpjBase;
        this.razaoSocial = razaoSocial;
        this.legalNatureModel = legalNatureModel;
        this.qualificacaoResponsavelModel = qualificacaoResponsavelModel;
        this.capitalSocial = capitalSocial;
        this.porteEmpresa = porteEmpresa;
        this.enteFederativoResponsavel = enteFederativoResponsavel;

        Estabelecimentos = new ArrayList<BranchModel>();

    }

    public void AddEstabeleciomento(BranchModel branchModel){
        Estabelecimentos.add(branchModel);
    }
    public void DeleteEstabelecimento(BranchModel branchModel){
        Estabelecimentos.remove(branchModel);
    }





}
