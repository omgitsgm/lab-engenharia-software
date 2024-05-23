package br.com.laudai.web.controller;

import br.com.laudai.domain.model.Paciente;
import br.com.laudai.domain.model.Radiologista;
import br.com.laudai.domain.service.AutenticacaoService;
import br.com.laudai.web.dto.input.AutenticacaoInput;
import br.com.laudai.web.dto.input.RadiologistaAutenticacaoInput;
import br.com.laudai.web.dto.output.PacienteOutput;
import br.com.laudai.web.dto.output.RadiologistaOutput;
import br.com.laudai.web.mapper.PacienteMapper;
import br.com.laudai.web.mapper.RadiologistaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping()
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;
    private final PacienteMapper pacienteMapper;
    private final RadiologistaMapper radiologistaMapper;

    @PostMapping("/paciente/autenticar")
    public ResponseEntity<PacienteOutput> autenticaPaciente(@RequestBody @Valid AutenticacaoInput autenticacaoInput) {

        Paciente paciente = autenticacaoService.autentica(autenticacaoInput.email(), autenticacaoInput.senha());
        PacienteOutput pacienteOutput = pacienteMapper.toPacienteOutput(paciente);

        return ResponseEntity.ok(pacienteOutput);

    }

    @PostMapping("/radiologista/autenticar")
    public ResponseEntity<RadiologistaOutput> autenticaRadiologista
            (@RequestBody @Valid RadiologistaAutenticacaoInput autenticacaoInput) {

        Radiologista radiologista = autenticacaoService
                .autenticaRadiologista(autenticacaoInput.crm(), autenticacaoInput.senha());

        RadiologistaOutput radiologistaOutput = radiologistaMapper.toRadiologistaOutput(radiologista);

        return ResponseEntity.ok(radiologistaOutput);


    }


}
