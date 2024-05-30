package com.example.ReceitaAPI.Dtos.Socio;

import java.util.Date;

public record SocioDto(

        Long cnpjBase,
        int identificador,
        String nomeRazao,
        String cpfCnpjSocio,
        Long qualificacao,
        Date dataEntradaSociedade,
        Long pais,
        String representanteLegal,
        String nomeRepresentante,
        Long qualificacaoRepresentante,
        int faixaEtaria
) {}
