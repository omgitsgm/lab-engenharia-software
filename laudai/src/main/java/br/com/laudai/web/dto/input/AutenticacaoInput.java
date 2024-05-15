package br.com.laudai.web.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AutenticacaoInput(
        @Email
        @NotBlank
        String email,
        @Size(min = 8, max = 100)
        @NotBlank
        String senha

) { }
