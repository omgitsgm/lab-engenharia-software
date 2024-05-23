package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Paciente;
import br.com.laudai.domain.model.Radiologista;

public interface AutenticacaoService {

    public Paciente autentica(String email, String senha);
    public Radiologista autenticaRadiologista(String crm, String senha);

}
