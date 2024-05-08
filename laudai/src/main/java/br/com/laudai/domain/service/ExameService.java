package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Exame;

public interface ExameService {
    void cadastrar(String nome);
    Exame findById(Integer id);

    Exame findByNome(String nome);

    void existsByNome(String nome);

}
