package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Consulta;
import br.com.laudai.domain.model.Exame;
import br.com.laudai.domain.model.Laboratorio;
import br.com.laudai.domain.model.Paciente;
import br.com.laudai.infra.repository.ConsultaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl implements ConsultaService{

    private final PacienteService pacienteService;
    private final ExameService exameService;
    private final LaboratorioService laboratorioService;
    private final ConsultaRepository consultaRepository;

    @Override
    @Transactional
    public void agendar(Integer pacienteId, Integer exameId, Integer laboratorioId, LocalDateTime dataHorario) {

        Paciente paciente = pacienteService.findById(pacienteId);
        Exame exame = exameService.findById(exameId);
        Laboratorio laboratorio = laboratorioService.findById(laboratorioId);

        // CHECAR SE NÃO TEM UMA CONSULTA AGENDADA NESSE LABORATÓRIO NA MESMA DATA E HORÁRIO
        laboratorio.getConsultas().forEach(consulta -> {
            if(consulta.getDataHorario().equals(dataHorario))
                throw new RuntimeException("Não é possível agendar uma consulta nesse laboratório nesta data e horário.");
        });


        // CHECAR SE O EXAME ESTÁ DISPONÍVEL NAQUELE LABORATÓRIO
        if(!laboratorio.getExames().contains(exame)){
            throw new RuntimeException("Este exame não está disponível nesse laboratório.");
        }

        Consulta consulta = new Consulta(paciente, exame, laboratorio, dataHorario);

        paciente.getConsultas().add(consulta);
        exame.getConsultas().add(consulta);
        laboratorio.getConsultas().add(consulta);

        consultaRepository.save(consulta);

    }

    @Override
    @Transactional
    public void cancelar(Integer pacienteId, Integer consultaId) {

        Consulta consulta = findById(consultaId);

        Paciente paciente = pacienteService.findById(pacienteId);

        if(!paciente.getConsultas().contains(consulta))
            throw new RuntimeException("Acesso negado. Não é possível realizar essa operação.");

        paciente.getConsultas().remove(consulta);
        laboratorioService.findById(consulta.getLaboratorio().getId())
                .getConsultas().remove(consulta);
        exameService.findById(consulta.getExame().getId())
                .getConsultas().remove(consulta);

        consultaRepository.delete(consulta);

    }

    @Override
    public Consulta findById(Integer id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível encontrar uma consulta com id " + id + "."));
    }

    @Override
    public List<Consulta> visualizarConsultas(Integer pacienteId) {

        Paciente paciente = pacienteService.findById(pacienteId);

        return consultaRepository.findAllByPaciente(paciente);

    }
}
