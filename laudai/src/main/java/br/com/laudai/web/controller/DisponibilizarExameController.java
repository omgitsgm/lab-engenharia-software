package br.com.laudai.web.controller;

import br.com.laudai.domain.service.DisponibilizarExameService;
import br.com.laudai.web.http.ResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/laboratorio/{idLaboratorio}/exame/{idExame}")
public class DisponibilizarExameController {

    private final DisponibilizarExameService disponibilizarExameService;

    @PutMapping
    public ResponseEntity<ResponseBody> adicionarExame(@PathVariable Integer idExame,
                                                       @PathVariable Integer idLaboratorio) {

        disponibilizarExameService.disponibilizar(idExame, idLaboratorio);

        ResponseBody responseBody = new ResponseBody(
                HttpStatus.OK.value(),
                "Exame adicionado a lista de exames disponíveis.",
                new ArrayList<>(List.of(URI.create("/laboratorio/" + idLaboratorio + "/exame")))
        );

        return ResponseEntity.ok(responseBody);

    }

    @DeleteMapping
    public ResponseEntity<ResponseBody> removerExame(@PathVariable Integer idExame, @PathVariable Integer idLaboratorio) {

        disponibilizarExameService.remover(idExame, idLaboratorio);

        ResponseBody responseBody = new ResponseBody(
                HttpStatus.OK.value(),
                "Exame removido da lista de exames disponíveis.",
                new ArrayList<>(List.of(URI.create("/laboratorio/" + idLaboratorio + "/exame")))
        );

        return ResponseEntity.ok(responseBody);

    }

}
