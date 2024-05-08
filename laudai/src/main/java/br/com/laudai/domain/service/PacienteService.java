package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PacienteService {

    void save(Paciente paciente);

    Paciente findById(Integer id);

    void deleteByCpf(String cpf);

    List<Paciente> getAll();

}
