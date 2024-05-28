package br.com.laudai.common;

import br.com.laudai.domain.model.Paciente;

import java.util.ArrayList;

public class PacienteTestConstants {

    public static final Paciente PACIENTE_ENTITY = new Paciente(
            1,
            "Nome",
            "49710302809",
            "nome@email.com",
            "12345678",
            new ArrayList<>()
    );

    public static final Paciente PACIENTE = new Paciente(
            null,
            "Nome",
            "49710302809",
            "nome@email.com",
            "12345678",
            new ArrayList<>()
    );

}
