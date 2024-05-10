package br.com.laudai.domain.service;

import br.com.laudai.domain.exception.PacienteCpfDuplicadoException;
import br.com.laudai.domain.exception.PacienteEmailDuplicadoException;
import br.com.laudai.domain.exception.PacienteInexistenteException;
import br.com.laudai.domain.model.Paciente;
import br.com.laudai.infra.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    @Override
    public Paciente save(Paciente paciente) {

        if(pacienteRepository.existsByCpf(paciente.getCpf()).equals(Boolean.TRUE))
            throw new PacienteCpfDuplicadoException(paciente.getCpf());
        else if(pacienteRepository.existsByEmail(paciente.getEmail()).equals(Boolean.TRUE))
            throw new PacienteEmailDuplicadoException(paciente.getEmail());

        return pacienteRepository.save(paciente);

    }

    @Override
    public Paciente findById(Integer id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteInexistenteException(id));
    }

    @Override
    public void deleteByCpf(String cpf) {

        pacienteRepository.deleteByCpf(cpf);

    }

    @Override
    public List<Paciente> getAll() {
        return pacienteRepository.findAll();
    }

}
