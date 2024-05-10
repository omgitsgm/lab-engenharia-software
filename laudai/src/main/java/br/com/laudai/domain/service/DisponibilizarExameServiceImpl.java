package br.com.laudai.domain.service;

import br.com.laudai.domain.exception.ExameDuplicadoException;
import br.com.laudai.domain.exception.ExameIndisponivelException;
import br.com.laudai.domain.model.Exame;
import br.com.laudai.domain.model.Laboratorio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        if(laboratorio.getExames().contains(exame))
            throw new ExameDuplicadoException(exame.getNome(), laboratorio.getNome());
        else
            laboratorio.getExames().add(exame);


    }

    @Transactional
    @Override
    public void remover(Integer idExame, Integer idLaboratorio) {

        Exame exame = exameService.findById(idExame);
        Laboratorio laboratorio = laboratorioService.findById(idLaboratorio);

        if(laboratorio.getExames().contains(exame))
            laboratorio.getExames().remove(exame);
        else
            throw new ExameIndisponivelException(exame.getNome(), laboratorio.getNome());

    }
}
