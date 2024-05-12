package br.com.laudai.web.dto.output;

public record PacienteOutput(
        Integer id,
        String nome,
        String cpf,
        String email

) { }
