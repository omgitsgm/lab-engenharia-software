package br.com.laudai.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RotasController {

    @GetMapping()
    public String index() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro.html";
    }

    @GetMapping("/pagina-inicial")
    public String paginaInicial() {
        return "pagina-inicial.html";
    }

    @GetMapping("/minhas-consultas")
    public String minhasConsultas() {
        return "minhas-consultas.html";
    }

    @GetMapping("/agendar-consulta")
    public String agendarConsulta() {
        return "agendar-consulta.html";
    }

}
