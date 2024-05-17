package com.example.ReceitaAPI.Dtos.Company;

import jakarta.validation.constraints.NotNull;

public record QualificacaoResponsavelDto(
        @NotNull(message = "o id não pode ser nulo")
        Integer id,
        @NotNull(message = "a description não pode ser nulo")
        String description
) {
}
