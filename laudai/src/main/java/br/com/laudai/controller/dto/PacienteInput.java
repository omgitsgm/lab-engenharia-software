package br.com.laudai.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record PacienteInput(

        @NotBlank
        String nome,

        @CPF
        @NotBlank
        String cpf,

        @Email
        @NotBlank
        String email,

        @Pattern(regexp = "^\\d{2}\\d{4,5}\\d{4}$")
        @NotBlank
        String telefone

) { }
