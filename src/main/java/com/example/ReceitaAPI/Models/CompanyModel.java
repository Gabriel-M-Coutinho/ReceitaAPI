package com.example.ReceitaAPI.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_Companies",uniqueConstraints = @UniqueConstraint(columnNames = "cnpjBase"))
public class CompanyModel {

    @Id
    @Column(nullable = false, unique = true)
    private String cnpjBase;

    @Column(nullable = false)
    private String razaoSocial;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LegalNatureId",referencedColumnName = "id")
    private LegalNatureModel legalNatureModel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Qualificacao_Responsavel_id",referencedColumnName = "id")
    private QualificacaoResponsavelModel qualificacaoResponsavelModel;

    @Column(nullable = false)
    private double capitalSocial;

    @ManyToOne
    @JoinColumn(name = "PorteEmpresaId" , referencedColumnName = "id")
    private PorteDaEmpresaModel empresaModel;

    private String enteFederativoResponsavel;

    @OneToMany(mappedBy = "cnpjBase",cascade = CascadeType.ALL)
    List<BranchModel> Estabelecimentos;


    public CompanyModel(String cnpjBase, String razaoSocial, LegalNatureModel legalNatureModel, QualificacaoResponsavelModel qualificacaoResponsavelModel, double capitalSocial, PorteDaEmpresaModel porteEmpresa, String enteFederativoResponsavel) {
        this.cnpjBase = cnpjBase;
        this.razaoSocial = razaoSocial;
        this.legalNatureModel = legalNatureModel;
        this.qualificacaoResponsavelModel = qualificacaoResponsavelModel;
        this.capitalSocial = capitalSocial;
        this.empresaModel = porteEmpresa;
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
