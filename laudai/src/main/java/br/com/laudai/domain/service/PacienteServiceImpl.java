package br.com.laudai.domain.service;

import br.com.laudai.domain.exception.*;
import br.com.laudai.domain.model.Consulta;
import br.com.laudai.domain.model.ImagemExame;
import br.com.laudai.domain.model.Paciente;
import br.com.laudai.domain.model.ResultadoExame;
import br.com.laudai.infra.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    @Override
    public Paciente save(Paciente paciente) {

        log.info("Checando se já existe um paciente com o mesmo CPF ou Email cadastrado.");
        if(pacienteRepository.existsByCpf(paciente.getCpf()).equals(Boolean.TRUE)) {
            log.error("Já existe um paciente com o mesmo CPF cadastrado.");
            throw new PacienteCpfDuplicadoException(paciente.getCpf());
        } else if(pacienteRepository.existsByEmail(paciente.getEmail()).equals(Boolean.TRUE)) {
            log.error("Já existe um paciente com o mesmo Email cadastrado.");
            throw new PacienteEmailDuplicadoException(paciente.getEmail());
        }

        log.info("Persistindo paciente...");
        return pacienteRepository.save(paciente);

    }

    @Override
    public Paciente findById(Integer id) {
        log.info("Procurando paciente...");
        return pacienteRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Paciente não existe.");
                    return new PacienteInexistenteException(id);
                });
    }

    @Override
    public List<Consulta> getConsultas(Integer pacienteId) {
        Paciente paciente = findById(pacienteId);

        log.info("Procurando consultas do paciente...");
        return paciente.getConsultas();
    }

    @Override
    public Paciente findByEmailAndSenha(String email, String senha) {
        log.info("Checando credenciais do paciente...");
        return pacienteRepository.findByEmailAndSenha(email, senha)
                .orElseThrow(() -> {
                    log.error("Credenciais inválidas.");
                    return new AutenticacaoException();
                });
    }

    @Override
    public ResultadoExame getResultadoExame(Integer idPaciente, Integer idConsulta) {

        Paciente paciente = findById(idPaciente);

        List<Consulta> consultas = paciente.getConsultas();
        Consulta consulta = consultas.stream()
                .filter(c -> c.getId().equals(idConsulta)).findFirst()
                .orElseThrow(AcessoNegadoException::new);

        if(consulta.getResultadoExame() == null) {
            throw new ResultadoIndisponivelException("O resultado para essa consulta ainda não está disponível.");
        }

        return consulta.getResultadoExame();

    }

}
