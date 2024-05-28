package br.com.laudai.domain.service;

import br.com.laudai.domain.exception.AcessoNegadoException;
import br.com.laudai.domain.exception.ConsultaIndisponivelException;
import br.com.laudai.domain.exception.ConsultaInexistenteException;
import br.com.laudai.domain.exception.ExameIndisponivelException;
import br.com.laudai.domain.model.Consulta;
import br.com.laudai.domain.model.Exame;
import br.com.laudai.domain.model.Laboratorio;
import br.com.laudai.domain.model.Paciente;
import br.com.laudai.infra.repository.ConsultaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl implements ConsultaService{

    private final PacienteService pacienteService;
    private final ExameService exameService;
    private final LaboratorioService laboratorioService;
    private final ConsultaRepository consultaRepository;

    @Override
    @Transactional
    public Consulta agendar(Integer pacienteId, Integer exameId, Integer laboratorioId, LocalDateTime dataHorario) {

        Paciente paciente = pacienteService.findById(pacienteId);
        Exame exame = exameService.findById(exameId);
        Laboratorio laboratorio = laboratorioService.findById(laboratorioId);

        log.info("Checando se não tem uma consulta agendada no laboratório selecionado na mesma data e horário.");
        laboratorio.getConsultas().forEach(consulta -> {
            if(consulta.getDataHorario().equals(dataHorario)) {
                log.error("Já existe uma consulta agendada no laboratório selecionado na mesma data e horário.");
                throw new ConsultaIndisponivelException();
            }
        });

        log.info("Checando se o exame selecionado está disponível no laboratório.");
        if(!laboratorio.getExames().contains(exame)){
            log.error("Exame não está disponível no laboratório.");
            throw new ExameIndisponivelException(exame.getNome(), laboratorio.getNome());
        }

        log.info("Gerando consulta...");
        Consulta consulta = new Consulta(paciente, exame, laboratorio, dataHorario);

        paciente.getConsultas().add(consulta);
        exame.getConsultas().add(consulta);
        laboratorio.getConsultas().add(consulta);

        log.info("Persistindo consulta...");
        return consultaRepository.save(consulta);

    }

    @Override
    @Transactional
    public void cancelar(Integer pacienteId, Integer consultaId) {

        Consulta consulta = findById(consultaId);

        Paciente paciente = pacienteService.findById(pacienteId);

        log.info("Checando se o paciente que está solicitando o cancelamento é o paciente que agendou a consulta.");
        if(!paciente.getConsultas().contains(consulta)) {
            log.error("Paciente que tentou cancelar a consulta não é o mesmo que a agendou.");
            throw new AcessoNegadoException();
        }

        paciente.getConsultas().remove(consulta);
        laboratorioService.findById(consulta.getLaboratorio().getId()).getConsultas().remove(consulta);
        exameService.findById(consulta.getExame().getId()).getConsultas().remove(consulta);

        log.info("Cancelando consulta...");
        consultaRepository.delete(consulta);

    }

    @Override
    public Consulta findById(Integer id) {
        log.info("Procurando consulta por id no banco de dados...");
        return consultaRepository.findById(id).orElseThrow(ConsultaInexistenteException::new);
    }

}
