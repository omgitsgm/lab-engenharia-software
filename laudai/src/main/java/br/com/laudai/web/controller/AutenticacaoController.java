package br.com.laudai.web.controller;

import br.com.laudai.domain.model.Paciente;
import br.com.laudai.domain.service.AutenticacaoService;
import br.com.laudai.web.dto.input.AutenticacaoInput;
import br.com.laudai.web.dto.output.PacienteOutput;
import br.com.laudai.web.mapper.PacienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;
    private final PacienteMapper pacienteMapper;

    @PostMapping
    public ResponseEntity<PacienteOutput> autentica(@RequestBody AutenticacaoInput autenticacaoInput) {

        Paciente paciente = autenticacaoService.autentica(autenticacaoInput.email(), autenticacaoInput.senha());
        PacienteOutput pacienteOutput = pacienteMapper.toPacienteOutput(paciente);

        return ResponseEntity.ok(pacienteOutput);

    }


}
