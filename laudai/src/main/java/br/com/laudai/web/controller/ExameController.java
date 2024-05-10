package br.com.laudai.web.controller;

import br.com.laudai.domain.model.Exame;
import br.com.laudai.domain.service.ExameService;
import br.com.laudai.web.dto.input.ExameInput;
import br.com.laudai.web.dto.output.ExameOutput;
import br.com.laudai.web.http.ResponseBody;
import br.com.laudai.web.mapper.ExameMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/exame")
public class ExameController {

    private final ExameService exameService;
    private final ExameMapper exameMapper;
    private static final String EXAME_URI = "/exame/";

    @PostMapping
    public ResponseEntity<ResponseBody> cadastrar(@RequestBody @Valid ExameInput exameInput) {

        Exame exame = exameService.cadastrar(exameInput.nome());
        ExameOutput exameOutput = exameMapper.toExameOutput(exame);

        URI uri = URI.create(EXAME_URI + exameOutput.id());
        ResponseBody responseBody = new ResponseBody(
                HttpStatus.CREATED.value(),
                "Exame cadastrado com sucesso.",
                 new ArrayList<>(List.of(exameOutput))
        );

        return ResponseEntity.created(uri).body(responseBody);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ExameOutput> findById(@PathVariable Integer id) {

        Exame exame = exameService.findById(id);
        ExameOutput exameOutput = exameMapper.toExameOutput(exame);

        return ResponseEntity.ok(exameOutput);

    }

    @GetMapping
    public ResponseEntity<List<ExameOutput>> findAll() {

        List<Exame> exameList = exameService.findAll();
        List<ExameOutput> exameOutputList = exameList.stream().map(exameMapper::toExameOutput).toList();

        return ResponseEntity.ok(exameOutputList);

    }

}
