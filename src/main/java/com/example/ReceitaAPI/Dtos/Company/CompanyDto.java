package com.example.ReceitaAPI.Dtos.Company;

import com.example.ReceitaAPI.Models.Estabelecimento.EstabelecimentoModel;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CompanyDto(
        @NotNull
        String cnpjBase,
        @NotNull
        String razaoSocial,
        @NotNull
        NaturezaLegalDto legalNature,
        @NotNull
        QualificacaoResponsavelDto qualificacaoResponsavel,
        double capitalSocial,
        @NotNull
        PorteDaEmpresaDto porteEmpresa,
        String enteFederativoResponsavel,
        List<EstabelecimentoModel> estabelecimentos


) {
}
