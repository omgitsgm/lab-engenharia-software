package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Paciente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AutenticacaoServiceImpl implements AutenticacaoService{

    private final PacienteService pacienteService;

    @Override
    public Paciente autentica(String email, String senha) {

        return pacienteService.findByEmailAndSenha(email, senha);

    }
}
