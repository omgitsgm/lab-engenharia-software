package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Paciente;
import br.com.laudai.domain.model.Radiologista;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AutenticacaoServiceImpl implements AutenticacaoService{

    private final PacienteService pacienteService;
    private final RadiologistaService radiologistaService;

    @Override
    public Paciente autentica(String email, String senha) {

        return pacienteService.findByEmailAndSenha(email, senha);

    }

    @Override
    public Radiologista autenticaRadiologista(String crm, String senha) {
        return radiologistaService.autenticar(crm, senha);
    }
}
