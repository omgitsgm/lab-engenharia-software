package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Exame;

import java.util.List;

public interface ExameService {
    Exame cadastrar(String nome);
    Exame findById(Integer id);

    Exame findByNome(String nome);

    List<Exame> findAll();

}
