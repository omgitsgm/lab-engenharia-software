package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Radiologista;

public interface RadiologistaService {

    Radiologista save(Radiologista radiologista);

    Radiologista autenticar(String crm, String senha);

}
