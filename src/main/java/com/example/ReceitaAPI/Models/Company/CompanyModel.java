package com.example.ReceitaAPI.Models.Company;

import com.example.ReceitaAPI.Models.Estabelecimento.EstabelecimentoModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@Entity
@Table(name = "tb_Companies", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cnpjBase"})
})


@NoArgsConstructor
@Getter
@Setter
public class CompanyModel {

    @Id
    @Column(nullable = false, unique = true)
    @JdbcTypeCode(SqlTypes.CHAR)
    private String cnpjBase;



    @Column(nullable = false)
    private String razaoSocial;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NaturezaLegalId",referencedColumnName = "id")
    private NaturezaLegalModel naturezaLegalModel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Qualificacao_Responsavel_id",referencedColumnName = "id")
    private QualificacaoResponsavelModel qualificacaoResponsavelModel;

    @Column(nullable = false)
    private double capitalSocial;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PorteEmpresaId" , referencedColumnName = "id")
    private PorteDaEmpresaModel empresaModel;

    private String enteFederativoResponsavel;

    @OneToMany(mappedBy = "cnpjBase",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    List<EstabelecimentoModel> Estabelecimentos;


    public CompanyModel(String cnpjBase, String razaoSocial, NaturezaLegalModel naturezaLegalModel, QualificacaoResponsavelModel qualificacaoResponsavelModel, double capitalSocial, PorteDaEmpresaModel porteEmpresa, String enteFederativoResponsavel) {
        this.cnpjBase = cnpjBase;
        this.razaoSocial = razaoSocial;
        this.naturezaLegalModel = naturezaLegalModel;
        this.qualificacaoResponsavelModel = qualificacaoResponsavelModel;
        this.capitalSocial = capitalSocial;
        this.empresaModel = porteEmpresa;
        this.enteFederativoResponsavel = enteFederativoResponsavel;

        Estabelecimentos = new ArrayList<EstabelecimentoModel>();

    }

    public void AddEstabeleciomento(EstabelecimentoModel estabelecimentoModel){
        Estabelecimentos.add(estabelecimentoModel);
    }
    public void DeleteEstabelecimento(EstabelecimentoModel estabelecimentoModel){
        Estabelecimentos.remove(estabelecimentoModel);
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        CompanyModel that = (CompanyModel) o;
        return getCnpjBase() != null && Objects.equals(getCnpjBase(), that.getCnpjBase());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
