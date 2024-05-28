package br.com.laudai.domain.service;

import br.com.laudai.web.feign.ModeloFeignClient;
import br.com.laudai.web.feign.model.ModeloResultadoInput;
import br.com.laudai.web.http.CustomMultipartFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;

@RequiredArgsConstructor
@Service
public class ModeloServiceImpl implements ModeloService {

    private final ModeloFeignClient modeloFeignClient;
    private final ImagemLocalStorageService imagemLocalStorageService;

    @Override
    public ModeloResultadoInput predict(String nomeArquivo) {

        Path path = imagemLocalStorageService.getArquivoPath(nomeArquivo);
        File file = new File(path.toUri());

        MultipartFile multipartFile = new CustomMultipartFile(file, file.getName());

        return modeloFeignClient.getPredict(multipartFile);

    }
}
