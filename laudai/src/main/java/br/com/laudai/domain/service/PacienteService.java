package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Paciente;

public interface PacienteService {

    Paciente save(Paciente paciente);

    Paciente findById(Integer id);

}
