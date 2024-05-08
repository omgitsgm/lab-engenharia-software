package br.com.laudai.controller;

import br.com.laudai.controller.dto.ExameInput;
import br.com.laudai.controller.dto.LaboratorioInput;
import br.com.laudai.controller.dto.LaboratorioOutput;
import br.com.laudai.controller.dto.PacienteInput;
import br.com.laudai.controller.util.LaboratorioMapper;
import br.com.laudai.domain.model.Laboratorio;
import br.com.laudai.domain.service.LaboratorioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/laboratorio")
public class LaboratorioController {

    private final LaboratorioService laboratorioService;
    private final LaboratorioMapper laboratorioMapper;

    public LaboratorioController(LaboratorioService laboratorioService, LaboratorioMapper laboratorioMapper) {
        this.laboratorioService = laboratorioService;
        this.laboratorioMapper = laboratorioMapper;
    }

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
