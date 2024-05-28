package br.com.laudai.web.controller;

import br.com.laudai.domain.model.Consulta;
import br.com.laudai.domain.model.Exame;
import br.com.laudai.domain.model.Laboratorio;
import br.com.laudai.domain.service.LaboratorioService;
import br.com.laudai.web.dto.input.LaboratorioInput;
import br.com.laudai.web.dto.output.ConsultaOutput;
import br.com.laudai.web.dto.output.ExameOutput;
import br.com.laudai.web.dto.output.LaboratorioOutput;
import br.com.laudai.web.http.ResponseBody;
import br.com.laudai.web.mapper.ConsultaMapper;
import br.com.laudai.web.mapper.ExameMapper;
import br.com.laudai.web.mapper.LaboratorioMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/laboratorio")
public class LaboratorioController {

    private final LaboratorioService laboratorioService;
    private final LaboratorioMapper laboratorioMapper;
    private final ExameMapper exameMapper;
    private final ConsultaMapper consultaMapper;

    private static final String LABORATORIO_URI = "/laboratorio/";

    @PostMapping
    public ResponseEntity<ResponseBody> save(@RequestBody @Valid LaboratorioInput laboratorioInput) {

        log.info("Inicializando método de cadastrar laboratório.");

        Laboratorio laboratorio = laboratorioService.save(laboratorioMapper.toLaboratorio(laboratorioInput));
        LaboratorioOutput laboratorioOutput = laboratorioMapper.toLaboratorioOutput(laboratorio);


        log.info("Montando resposta para a requisição de cadastrar laboratório.");
        URI uri = URI.create(LABORATORIO_URI + laboratorioOutput.id());

        ResponseBody responseBody = new ResponseBody(
                HttpStatus.CREATED.value(),
                "Laboratório cadastrado com sucesso.",
                new ArrayList<>(List.of(laboratorioOutput))
        );

        log.info("Finalizando método de cadastrar laboratório.");
        return ResponseEntity.created(uri).body(responseBody);

    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratorioOutput> findById(@PathVariable Integer id) {

        log.info("Inicializando método de procurar laboratório por id.");

        Laboratorio laboratorio = laboratorioService.findById(id);
        LaboratorioOutput laboratorioOutput = laboratorioMapper.toLaboratorioOutput(laboratorio);

        log.info("Finalizando método de procurar laboratório por id.");
        return ResponseEntity.ok().body(laboratorioOutput);

    }

    @GetMapping("/{id}/exame")
    public ResponseEntity<List<ExameOutput>> getExamesDisponiveis(@PathVariable Integer id) {

        log.info("Inicializando método de procurar exames disponíveis em laboratório.");
        List<Exame> examesDisponiveis = laboratorioService.findExamesDisponiveis(id);

        List<ExameOutput> exameOutputList = examesDisponiveis.stream().map(exameMapper::toExameOutput).toList();

        log.info("Finalizando método de procurar exames disponíveis em laboratório.");
        return ResponseEntity.ok(exameOutputList);

    }

    @GetMapping("/exame")
    public ResponseEntity<List<LaboratorioOutput>> findAllByExame(@RequestParam(name = "nome") String nome) {

        log.info("Inicializando método de procurar todos os laboratórios por exame.");

        List<Laboratorio> laboratorios = laboratorioService.findAllByExame(nome);

        List<LaboratorioOutput> laboratorioOutputList = laboratorios.stream()
                .map(laboratorioMapper::toLaboratorioOutput).toList();

        log.info("Finalizando método de procurar todos os laboratórios por exame.");
        return ResponseEntity.ok(laboratorioOutputList);

    }

    @GetMapping("/{id}/consulta")
    public ResponseEntity<List<ConsultaOutput>> getConsultas(@PathVariable Integer id) {

        log.info("Inicializando método de procurar todas as consultas de um laboratório.");

        List<Consulta> consultaList = laboratorioService.getConsultasById(id);

        List<ConsultaOutput> consultaOutputList = consultaList.stream().map(consultaMapper::toConsultaOutput).toList();

        log.info("Finalizando método de procurar todas as consultas de um laboratório.");
        return ResponseEntity.ok(consultaOutputList);

    }

    @GetMapping
    public List<LaboratorioOutput> getAllLaboratorios() {

        log.info("Inicializando método de procurar todos os laboratórios.");

        List<Laboratorio> laboratorioList = laboratorioService.findAll();

        log.info("Finalizando método de procurar todos os laboratórios.");
        return laboratorioList.stream().map(laboratorioMapper::toLaboratorioOutput).toList();

    }



}
