package br.com.laudai.web.controller;

import br.com.laudai.web.dto.input.ExameInput;
import br.com.laudai.web.dto.input.LaboratorioInput;
import br.com.laudai.web.dto.output.LaboratorioOutput;
import br.com.laudai.web.http.ResponseBody;
import br.com.laudai.web.mapper.LaboratorioMapper;
import br.com.laudai.domain.model.Laboratorio;
import br.com.laudai.domain.service.LaboratorioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/laboratorio")
public class LaboratorioController {

    private final LaboratorioService laboratorioService;
    private final LaboratorioMapper laboratorioMapper;

    private static final String LABORATORIO_URI = "/laboratorio/";

    @PostMapping
    public ResponseEntity<ResponseBody> save(@RequestBody @Valid LaboratorioInput laboratorioInput) {

        Laboratorio laboratorio = laboratorioService.save(laboratorioMapper.toLaboratorio(laboratorioInput));
        LaboratorioOutput laboratorioOutput = laboratorioMapper.toLaboratorioOutput(laboratorio);

        URI uri = URI.create(LABORATORIO_URI + laboratorioOutput.id());

        ResponseBody responseBody = new ResponseBody(
                HttpStatus.CREATED.value(),
                "Laboratório cadastrado com sucesso.",
                new ArrayList<>(List.of(laboratorioOutput))
        );

        return ResponseEntity.created(uri).body(responseBody);

    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratorioOutput> findById(@PathVariable Integer id) {

        Laboratorio laboratorio = laboratorioService.findById(id);
        LaboratorioOutput laboratorioOutput = laboratorioMapper.toLaboratorioOutput(laboratorio);

        return ResponseEntity.ok().body(laboratorioOutput);

    }

    @PostMapping("/{laboratorioId}/exame")
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Exame adicionado com sucesso.")
    public void adicionarExame(@PathVariable Integer laboratorioId, @RequestBody @Valid ExameInput exameInput) {

        laboratorioService.adicionarExame(laboratorioId, exameInput.nome());

    }

    @GetMapping("/exame")
    public ResponseEntity<List<LaboratorioOutput>> buscarPorExame(@RequestParam(name = "nome") String nome) {

        List<Laboratorio> laboratorios = laboratorioService.findAllByExame(nome);

        List<LaboratorioOutput> laboratorioOutputList = laboratorios.stream()
                .map(laboratorioMapper::toLaboratorioOutput).toList();

        return ResponseEntity.ok(laboratorioOutputList);

    }


}
