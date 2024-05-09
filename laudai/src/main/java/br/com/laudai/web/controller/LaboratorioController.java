package br.com.laudai.web.controller;

import br.com.laudai.web.dto.input.ExameInput;
import br.com.laudai.web.dto.input.LaboratorioInput;
import br.com.laudai.web.dto.output.LaboratorioOutput;
import br.com.laudai.web.mapper.LaboratorioMapper;
import br.com.laudai.domain.model.Laboratorio;
import br.com.laudai.domain.service.LaboratorioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/laboratorio")
public class LaboratorioController {

    private final LaboratorioService laboratorioService;
    private final LaboratorioMapper laboratorioMapper;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Laboratório cadastrado com sucesso.")
    public void save(@RequestBody @Valid LaboratorioInput laboratorioInput) {

        laboratorioService.save(laboratorioMapper.toLaboratorio(laboratorioInput));

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