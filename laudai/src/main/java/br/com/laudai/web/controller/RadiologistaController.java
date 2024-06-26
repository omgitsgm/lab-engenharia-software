package br.com.laudai.web.controller;

import br.com.laudai.domain.model.Laboratorio;
import br.com.laudai.domain.model.Radiologista;
import br.com.laudai.domain.service.LaboratorioService;
import br.com.laudai.domain.service.RadiologistaService;
import br.com.laudai.web.dto.input.RadiologistaInput;
import br.com.laudai.web.dto.output.RadiologistaOutput;
import br.com.laudai.web.mapper.RadiologistaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/radiologista")
public class RadiologistaController {

    private final LaboratorioService laboratorioService;
    private final RadiologistaMapper radiologistaMapper;
    private final RadiologistaService radiologistaService;

    @PostMapping
    public ResponseEntity<RadiologistaOutput> cadastrar(@RequestBody @Valid RadiologistaInput radiologistaInput) {

        log.info("Inicializando método de cadastrar radiologista.");
        Laboratorio laboratorio = laboratorioService.findById(radiologistaInput.laboratorioId());
        Radiologista radiologista = radiologistaMapper.toRadiologista(radiologistaInput);
        radiologista.setLaboratorio(laboratorio);

        Radiologista savedRadiologista = radiologistaService.save(radiologista);
        RadiologistaOutput radiologistaOutput = radiologistaMapper.toRadiologistaOutput(savedRadiologista);

        log.info("Finalizando método de cadastrar radiologista.");
        return ResponseEntity.created(URI.create("/radiologista/" + radiologistaOutput.id())).body(radiologistaOutput);

    }



}
