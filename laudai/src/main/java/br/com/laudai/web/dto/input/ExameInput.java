package br.com.laudai.web.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExameInput(
        @NotBlank
        String nome
) {
}
