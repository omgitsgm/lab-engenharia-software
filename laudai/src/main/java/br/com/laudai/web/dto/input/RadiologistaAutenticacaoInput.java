package br.com.laudai.web.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RadiologistaAutenticacaoInput(

        @NotBlank
        String crm,
        @Size(min = 8, max = 100)
        @NotBlank
        String senha

) {
}
