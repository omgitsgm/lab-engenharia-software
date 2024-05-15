package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Paciente;

public interface AutenticacaoService {

    public Paciente autentica(String email, String senha);

}
