package br.com.laudai.domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ImagemLocalStorageService implements ImagemStorageService{

    @Value("${laudai.storage.local.directory}")
    private Path localDirectory;

    @Override
    public void armazenar(String nomeArquivo, InputStream inputStream) {

        Path path = getArquivoPath(nomeArquivo);

        try {
            FileCopyUtils.copy(inputStream, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível armazenar o arquivo.");
        }

    }

    private Path getArquivoPath(String nomeArquivo) {
        return localDirectory.resolve(Path.of(nomeArquivo));
    }
}
