package br.com.laudai.domain.exception;

public class ArquivoNaoEncontradoException extends RuntimeException {

    public ArquivoNaoEncontradoException() {
        super("O arquivo não foi encontrado.");
    }
}
