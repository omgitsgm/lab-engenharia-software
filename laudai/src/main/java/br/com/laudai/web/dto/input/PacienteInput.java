package br.com.laudai.web.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

        @Size(min = 8, max = 100)
        @NotBlank
        String senha

) { }
