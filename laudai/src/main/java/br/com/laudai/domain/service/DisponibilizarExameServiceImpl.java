package br.com.laudai.domain.service;

import br.com.laudai.domain.exception.ExameDuplicadoException;
import br.com.laudai.domain.exception.ExameIndisponivelException;
import br.com.laudai.domain.model.Exame;
import br.com.laudai.domain.model.Laboratorio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DisponibilizarExameServiceImpl implements DisponibilizarExameService{

    private final ExameService exameService;
    private final LaboratorioService laboratorioService;

    @Transactional
    @Override
    public void disponibilizar(Integer idExame, Integer idLaboratorio) {

        Exame exame = exameService.findById(idExame);
        Laboratorio laboratorio = laboratorioService.findById(idLaboratorio);

        log.info("Checando se o laboratório já contém aquele exame disponibilizado...");
        if(laboratorio.getExames().contains(exame)) {
            log.error("O laboratório já contém o exame.");
            throw new ExameDuplicadoException(exame.getNome(), laboratorio.getNome());
        } else {
            log.info("Adicionando exame no laboratório...");
            laboratorio.getExames().add(exame);
        }


    }

    @Transactional
    @Override
    public void remover(Integer idExame, Integer idLaboratorio) {

        Exame exame = exameService.findById(idExame);
        Laboratorio laboratorio = laboratorioService.findById(idLaboratorio);

        log.info("Checando se o laboratório contém exame...");
        if(laboratorio.getExames().contains(exame)) {
            log.info("Removendo exame do laboratório...");
            laboratorio.getExames().remove(exame);
        }
        else {
            log.error("O exame não está disponível no laboratório.");
            throw new ExameIndisponivelException(exame.getNome(), laboratorio.getNome());
        }

    }
}
