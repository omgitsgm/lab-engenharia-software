package br.com.laudai.domain.service;

public interface DisponibilizarExameService {

    void disponibilizar(Integer idExame, Integer idLaboratorio);
    void remover(Integer idExame, Integer idLaboratorio);

}
