package br.com.laudai.web.controller;

import br.com.laudai.domain.model.ImagemExame;
import br.com.laudai.domain.service.ImagemStorageService;
import br.com.laudai.domain.service.ResultadoExameService;
import br.com.laudai.web.dto.input.ImagemExameInput;
import br.com.laudai.web.dto.output.ImagemExameOutput;
import br.com.laudai.web.mapper.ImagemExameMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/consulta/{idConsulta}/resultado")
public class ResultadoExameController {

    private final ResultadoExameService resultadoExameService;
    private final ImagemExameMapper imagemExameMapper;
    private final ImagemStorageService imagemStorageService;

    @PutMapping(value = "/imagem", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImagemExameOutput> atualizarImagemExame(@PathVariable Integer idConsulta,
                                                                  @Valid ImagemExameInput imagemExameInput) {

        log.info("Inicializando método de atualizar a imagem de um exame");
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

        log.info("Finalizando método de atualizar a imagem de um exame");
        return ResponseEntity.ok(imagemExameOutput);

    }

    @GetMapping(value = "/imagem", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImagemExameOutput> buscar(@PathVariable Integer idConsulta) {

        ImagemExame imagemExame = resultadoExameService.buscar(idConsulta);
        ImagemExameOutput imagemExameOutput = imagemExameMapper.toImagemExameOutput(imagemExame);

        return ResponseEntity.ok(imagemExameOutput);

    }

    @GetMapping(value = "/imagem")
    public ResponseEntity<InputStreamResource> disponibilizarImagem(@PathVariable Integer idConsulta,
                                                                    @RequestHeader(name = "accept") String acceptHeader) {
        try {
            ImagemExame imagemExame = resultadoExameService.buscar(idConsulta);

            MediaType mediaType = MediaType.parseMediaType(imagemExame.getContentType());
            List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);

            verificarCompatibilidadeMediaType(mediaType, mediaTypesAceitas);

            InputStream inputStream = imagemStorageService.recuperar(imagemExame.getNomeArquivo());

            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(new InputStreamResource(inputStream));

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    private void verificarCompatibilidadeMediaType(MediaType mediaType, List<MediaType> mediaTypesAceitas) {

        boolean compativel = mediaTypesAceitas.stream()
                .anyMatch(mediaTypesAceita -> mediaTypesAceita.isCompatibleWith(mediaType));

        if(!compativel)
            throw new RuntimeException("A imagem não está no mediatype aceito pelo consumidor da API.");

    }

}
