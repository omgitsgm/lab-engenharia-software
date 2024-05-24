package br.com.laudai.domain.service;

import java.io.InputStream;

public interface ImagemStorageService {

    void armazenar( String nomeArquivo, InputStream inputStream);

}
