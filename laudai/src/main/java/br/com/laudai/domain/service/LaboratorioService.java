package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Laboratorio;

import java.util.List;

public interface LaboratorioService {

    void save(Laboratorio laboratorio);

    Laboratorio findById(Integer id);

    void adicionarExame(Integer laboratorioId, String nomeExame);

    List<Laboratorio> findAllByExame(String nome);
}
