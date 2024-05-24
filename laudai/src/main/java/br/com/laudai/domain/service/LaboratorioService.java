package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Consulta;
import br.com.laudai.domain.model.Exame;
import br.com.laudai.domain.model.Laboratorio;

import java.util.List;

public interface LaboratorioService {

    Laboratorio save(Laboratorio laboratorio);

    Laboratorio findById(Integer id);

    List<Laboratorio> findAllByExame(String nome);

    List<Exame> findExamesDisponiveis(Integer laboratorioId);

    List<Consulta> getConsultasById(Integer laboratorioId);

}
