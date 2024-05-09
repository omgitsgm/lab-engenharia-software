package br.com.laudai.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ConsultaOutput(
        String nomePaciente,
        String nomeExame,
        String nomeLaboratorio,

        @JsonFormat(pattern = "dd/MM/uuuu HH:mm")
        LocalDateTime dataHorario
) { }