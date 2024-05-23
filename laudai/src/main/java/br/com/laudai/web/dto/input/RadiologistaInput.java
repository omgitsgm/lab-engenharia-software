package br.com.laudai.web.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RadiologistaInput(

        @NotNull
        Integer laboratorioId,

        @NotBlank
        String nome,

        // Formato de CRM
        @NotBlank
        String crm,

        @Size(min = 8, max = 100)
        @NotBlank
        String senha

) { }
