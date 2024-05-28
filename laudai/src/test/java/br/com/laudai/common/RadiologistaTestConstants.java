package br.com.laudai.common;

import br.com.laudai.domain.model.Laboratorio;
import br.com.laudai.domain.model.Paciente;
import br.com.laudai.domain.model.Radiologista;

import java.util.ArrayList;

public class RadiologistaTestConstants {

    public static final Radiologista RADIOLOGISTA_ENTITY = new Radiologista(
            1,
            "Nome",
            "12003193",
            "12345678",
            new ArrayList<>(),
            new Laboratorio()
    );

    public static final Radiologista RADIOLOGISTA = new Radiologista(
            null,
            "Nome",
            "12003193",
            "12345678",
            new ArrayList<>(),
            new Laboratorio()
    );

}
