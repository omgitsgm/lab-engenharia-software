package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Consulta;
import br.com.laudai.domain.model.Paciente;

import java.util.List;

public interface PacienteService {

    Paciente save(Paciente paciente);

    Paciente findById(Integer pacienteId);

    List<Consulta> getConsultas(Integer pacienteId);

}
