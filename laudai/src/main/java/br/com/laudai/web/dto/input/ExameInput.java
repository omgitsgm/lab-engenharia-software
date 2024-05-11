package br.com.laudai.web.dto.input;

import jakarta.validation.constraints.NotBlank;

public record ExameInput(
        @NotBlank
        String nome
) {
}
