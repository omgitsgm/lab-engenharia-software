package br.com.laudai.web.dto.input;

import jakarta.validation.constraints.NotBlank;

public record LaboratorioInput(

        @NotBlank
        String nome

) { }
