package br.com.laudai.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record LaboratorioInput(

        @NotBlank
        String nome

) { }
