package br.com.laudai.domain.service;

import br.com.laudai.domain.exception.ArquivoNaoEncontradoException;
import br.com.laudai.domain.exception.ModeloApiException;
import br.com.laudai.web.feign.ModeloFeignClient;
import br.com.laudai.web.feign.model.ModeloResultadoInput;
import br.com.laudai.web.http.CustomMultipartFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;

@Slf4j
@RequiredArgsConstructor
@Service
public class ModeloServiceImpl implements ModeloService {

    private final ModeloFeignClient modeloFeignClient;
    private final ImagemLocalStorageService imagemLocalStorageService;

    @Override
    public ModeloResultadoInput predict(String nomeArquivo) {

        Path path = imagemLocalStorageService.getArquivoPath(nomeArquivo);
        File file = new File(path.toUri());

        if(!file.exists()) {
            log.error("O arquivo " + file.getName() + " não existe.");
            throw new ArquivoNaoEncontradoException();
        }
        log.info("Arquivo encontrado no armazenamento local.");
        log.debug(path.toString());

        MultipartFile multipartFile = new CustomMultipartFile(file, file.getName());

        try {
            log.info("Enviando requisição para API do Modelo...");
            ResponseEntity<ModeloResultadoInput> predict = modeloFeignClient.getPredict(multipartFile);
            log.info("A requisição foi processada com sucesso.");
            log.info("Status code: " + predict.getStatusCode().toString());
            return predict.getBody();
        } catch (Exception e) {
            log.error("Problema ao estabelecer contato com a API do Modelo.");
            throw new ModeloApiException();
        }

    }

}
