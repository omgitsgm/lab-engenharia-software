package br.com.laudai.common;

import br.com.laudai.domain.model.Consulta;
import br.com.laudai.domain.model.Exame;
import br.com.laudai.domain.model.Laboratorio;
import br.com.laudai.domain.model.Radiologista;

import java.util.ArrayList;
import java.util.List;

import static br.com.laudai.common.ExameTestConstants.EXAME_ENTITY;

public class LaboratorioTestConstants {

    public static final Laboratorio LABORATORIO_ENTITY = new Laboratorio(
            1,
            "Nome do Laboratório",
            new ArrayList<Consulta>(),
            List.of(EXAME_ENTITY),
            new ArrayList<Radiologista>()
    );

    public static final Laboratorio LABORATORIO = new Laboratorio(
            null,
            "Nome do Laboratório",
            new ArrayList<Consulta>(),
            new ArrayList<Exame>(),
            new ArrayList<Radiologista>()
    );

}
