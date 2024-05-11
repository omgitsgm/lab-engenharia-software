package br.com.laudai.web.dto.output;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ConsultaOutput(
        Integer id,
        String nomePaciente,
        String nomeExame,
        String nomeLaboratorio,

        @JsonFormat(pattern = "dd/MM/uuuu HH:mm")
        LocalDateTime dataHorario
) { }
