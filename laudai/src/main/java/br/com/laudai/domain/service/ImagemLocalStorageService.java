package br.com.laudai.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Service
public class ImagemLocalStorageService implements ImagemStorageService{

    @Value("${laudai.storage.local.directory}")
    private Path localDirectory;

    @Override
    public void armazenar(String nomeArquivo, InputStream inputStream) {

        Path path = getArquivoPath(nomeArquivo);

        try {
            log.info("Criando arquivo...");
            FileCopyUtils.copy(inputStream, Files.newOutputStream(path));
        } catch (IOException e) {
            log.error("Erro ao criar o arquivo.");
            throw new RuntimeException("Não foi possível armazenar o arquivo: " + e);
        }

    }

    @Override
    public void remover(String nomeArquivo) {

        Path path = getArquivoPath(nomeArquivo);

        try {
            log.info("Excluindo arquivo...");
            Files.deleteIfExists(path);
        } catch (IOException e) {
            log.error("Erro ao excluir o arquivo...");
            throw new RuntimeException("Não foi possível excluir o arquivo.");
        }

    }

    @Override
    public InputStream recuperar(String nomeArquivo) {
        Path path = getArquivoPath(nomeArquivo);

        try {
            log.info("Buscando arquivo...");
            return Files.newInputStream(path);
        } catch (IOException e) {
            log.error("Erro ao buscar o arquivo.");
            throw new RuntimeException("Não foi possível recuperar o arquivo.");
        }

    }

    public Path getArquivoPath(String nomeArquivo) {
        return localDirectory.resolve(Path.of(nomeArquivo));
    }
}
