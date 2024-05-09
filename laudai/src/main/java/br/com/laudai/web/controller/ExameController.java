package br.com.laudai.web.controller;

import br.com.laudai.web.dto.input.ExameInput;
import br.com.laudai.domain.service.ExameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
@RequestMapping("/exame")
public class ExameController {

    private final ExameService exameService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Exame cadastrado com sucesso.")
    public void cadastrar(@RequestBody @Valid ExameInput exameInput) {

        exameService.cadastrar(exameInput.nome());

    }

}
