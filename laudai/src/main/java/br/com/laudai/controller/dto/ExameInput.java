package br.com.laudai.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExameInput(
        @NotBlank
        String nome
) {
}
