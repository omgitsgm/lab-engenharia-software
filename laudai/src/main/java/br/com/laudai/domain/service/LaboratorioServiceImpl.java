package br.com.laudai.domain.service;

import br.com.laudai.domain.exception.LaboratorioInexistenteException;
import br.com.laudai.domain.model.Consulta;
import br.com.laudai.domain.model.Exame;
import br.com.laudai.domain.model.Laboratorio;
import br.com.laudai.infra.repository.ConsultaRepository;
import br.com.laudai.infra.repository.LaboratorioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LaboratorioServiceImpl implements LaboratorioService{

    private final LaboratorioRepository laboratorioRepository;
    private final ExameService exameService;
    private final ConsultaRepository consultaRepository;

    @Override
    public Laboratorio save(Laboratorio laboratorio) {
        log.info("Persistindo laboratório...");
        return laboratorioRepository.save(laboratorio);

    }

    @Override
    public Laboratorio findById(Integer id) {
        log.info("Procurando laboratório...");
        return laboratorioRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Laboratório não existe.");
                    return new LaboratorioInexistenteException(id);
                });
    }

    @Override
    public List<Laboratorio> findAllByExame(String nome) {

        log.info("Procurando laboratórios por exame...");
        Exame exame = exameService.findByNome(nome);

        return exame.getLaboratorios();

    }

    @Override
    public List<Exame> findExamesDisponiveis(Integer laboratorioId) {

        log.info("Procurando exames disponíveis...");
        Laboratorio laboratorio = findById(laboratorioId);

        return laboratorio.getExames();

    }

    @Override
    public List<Consulta> getConsultasById(Integer laboratorioId) {

        log.info("Procurando consultas...");
        Laboratorio laboratorio = findById(laboratorioId);

        return consultaRepository.findAllByLaboratorio(laboratorio);

    }
}
