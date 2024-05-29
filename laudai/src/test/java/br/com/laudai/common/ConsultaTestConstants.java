package br.com.laudai.common;

import br.com.laudai.domain.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static br.com.laudai.common.ExameTestConstants.EXAME_ENTITY;
import static br.com.laudai.common.LaboratorioTestConstants.LABORATORIO_ENTITY;
import static br.com.laudai.common.PacienteTestConstants.PACIENTE_ENTITY;

public class ConsultaTestConstants {

    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();
    public static final Consulta CONSULTA_ENTITY_WITHOUT_RESULTADO = new Consulta(
            1,
            PACIENTE_ENTITY,
            EXAME_ENTITY,
            LABORATORIO_ENTITY,
            LOCAL_DATE_TIME,
            null,
            null
    );

    public static final Consulta CONSULTA = new Consulta(
            null,
            new Paciente(),
            new Exame(),
            new Laboratorio(),
            null,
            null,
            null
    );

}
