package br.com.laudai.domain.service;

import java.io.InputStream;

public interface ImagemStorageService {

    void armazenar( String nomeArquivo, InputStream inputStream);

    void remover(String nomeArquivo);

    default void substituir(String nomeArquivoAntigo, String nomeArquivoNovo, InputStream inputStreamArquivoNovo){
        this.armazenar(nomeArquivoNovo, inputStreamArquivoNovo);

        if(nomeArquivoAntigo != null) {
            this.remover(nomeArquivoAntigo);
        }
    }

}
