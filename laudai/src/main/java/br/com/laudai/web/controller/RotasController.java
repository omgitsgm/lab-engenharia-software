package br.com.laudai.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class RotasController {

    @GetMapping()
    public String index() {
        log.info("Renderizando index.html...");
        return "index.html";
    }

    @GetMapping("/login")
    public String login() {
        log.info("Renderizando login.html...");
        return "login.html";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        log.info("Renderizando cadastro.html...");
        return "cadastro.html";
    }

    @GetMapping("/pagina-inicial")
    public String paginaInicial() {
        log.info("Renderizando pagina-inicial.html...");
        return "pagina-inicial.html";
    }

    @GetMapping("/minhas-consultas")
    public String minhasConsultas() {
        log.info("Renderizando minhas-consultas.html...");
        return "minhas-consultas.html";
    }

    @GetMapping("/agendar-consulta")
    public String agendarConsulta() {
        log.info("Renderizando agendar-consulta.html...");
        return "agendar-consulta.html";
    }

}
