package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Consulta;
import br.com.laudai.domain.model.Paciente;
import br.com.laudai.domain.model.ResultadoExame;

import javax.xml.transform.Result;
import java.util.List;

public interface PacienteService {

    Paciente save(Paciente paciente);

    Paciente findById(Integer pacienteId);

    List<Consulta> getConsultas(Integer pacienteId);

    Paciente findByEmailAndSenha(String email, String senha);

    ResultadoExame getResultadoExame(Integer idPaciente, Integer idConsulta);

}
