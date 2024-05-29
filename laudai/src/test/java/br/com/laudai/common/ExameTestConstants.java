package br.com.laudai.common;

import br.com.laudai.domain.model.Exame;
import br.com.laudai.domain.model.Paciente;

import java.util.ArrayList;

public class ExameTestConstants {

    public static final Exame EXAME_ENTITY = new Exame(
            1,
            "Nome do Exame",
            new ArrayList<>(),
            new ArrayList<>()
    );

    public static final Exame EXAME = new Exame(
            null,
            "Nome do Exame",
            new ArrayList<>(),
            new ArrayList<>()
    );

}
