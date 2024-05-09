package br.com.laudai.controller;

import br.com.laudai.controller.dto.PacienteInput;
import br.com.laudai.controller.util.PacienteMapper;
import br.com.laudai.domain.model.Paciente;
import br.com.laudai.domain.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;
    private final PacienteMapper pacienteMapper;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Paciente cadastrado com sucesso.")
    public void save(@RequestBody @Valid PacienteInput pacienteInput) {

            pacienteService.save(pacienteMapper.toPaciente(pacienteInput));

    }

    @DeleteMapping("/{cpf}")
    public void deleteByCpf(@PathVariable String cpf) {

        pacienteService.deleteByCpf(cpf);

    }

    @GetMapping
    public List<Paciente> getAll() {

        return pacienteService.getAll();

    }

}
