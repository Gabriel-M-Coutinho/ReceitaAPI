package com.example.ReceitaAPI.Dtos.Company;

import jakarta.validation.constraints.NotNull;

public record PorteDaEmpresaDto(
        @NotNull(message = "id não pode ser nulo")
        Integer id,

        @NotNull(message = "a description não pode ser null")
        String description
) {
}
