package br.com.laudai.web.controller;

import br.com.laudai.domain.model.ImagemExame;
import br.com.laudai.domain.service.ImagemStorageService;
import br.com.laudai.domain.service.ResultadoExameService;
import br.com.laudai.domain.service.ResultadoExameServiceImpl;
import br.com.laudai.web.dto.input.ImagemExameInput;
import br.com.laudai.web.dto.output.ImagemExameOutput;
import br.com.laudai.web.mapper.ImagemExameMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/consulta/{idConsulta}/resultado")
public class ResultadoExameController {

    private final ResultadoExameService resultadoExameService;
    private final ImagemExameMapper imagemExameMapper;

    @PutMapping(value = "/imagem", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImagemExameOutput> atualizarImagemExame(@PathVariable Integer idConsulta,
                                                                  @Valid ImagemExameInput imagemExameInput) {

        MultipartFile arquivo = imagemExameInput.arquivo();

        String originalFilename = UUID.randomUUID().toString() + "_" + arquivo.getOriginalFilename();
        String contentType = arquivo.getContentType();
        long arquivoSize = arquivo.getSize();

        ImagemExame imagemExame = new ImagemExame(originalFilename, contentType, arquivoSize);

        ImagemExame savedImagemExame = null;
        try {
            savedImagemExame = resultadoExameService.save(imagemExame, idConsulta, arquivo.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível armazenar a imagem");
        }
        log.info(savedImagemExame.toString());

        ImagemExameOutput imagemExameOutput = imagemExameMapper.toImagemExameOutput(savedImagemExame);


        return ResponseEntity.ok(imagemExameOutput);

    }

    @GetMapping("/imagem")
    public ResponseEntity<ImagemExameOutput> buscar(@PathVariable Integer idConsulta) {

        ImagemExame imagemExame = resultadoExameService.buscar(idConsulta);
        ImagemExameOutput imagemExameOutput = imagemExameMapper.toImagemExameOutput(imagemExame);

        return ResponseEntity.ok(imagemExameOutput);

    }

}
