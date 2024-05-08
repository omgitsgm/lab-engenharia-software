package br.com.laudai.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ConsultaInput(

        @NotNull
        Integer exameId,

        @NotNull
        Integer laboratorioId,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/uuuu HH:mm")
        LocalDateTime dataHorario

) { }
