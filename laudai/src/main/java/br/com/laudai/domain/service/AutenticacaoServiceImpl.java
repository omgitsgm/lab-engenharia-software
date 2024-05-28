package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Paciente;
import br.com.laudai.domain.model.Radiologista;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AutenticacaoServiceImpl implements AutenticacaoService{

    private final PacienteService pacienteService;
    private final RadiologistaService radiologistaService;

    @Override
    public Paciente autentica(String email, String senha) {
        log.info("Autenticando paciente...");
        return pacienteService.findByEmailAndSenha(email, senha);

    }

    @Override
    public Radiologista autenticaRadiologista(String crm, String senha) {
        log.info("Autenticando radiologista...");
        return radiologistaService.autenticar(crm, senha);
    }
}
