package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Paciente;
import br.com.laudai.infra.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository){
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public void save(Paciente paciente) {

        pacienteRepository.save(paciente);

    }

    @Override
    public Paciente findById(Integer id) {
        // CRIAR EXCEPTION PARA CASO NÃO ENCONTRE O PACIENTE
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não existe um paciente com id " + id + "."));
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
