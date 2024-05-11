package br.com.laudai.domain.exception;

public class ExameInexistenteException extends RuntimeException {

    public ExameInexistenteException(Integer id) {
        super("Não foi possível encontrar um exame com o id " + id + ".");
    }

    public ExameInexistenteException(String nome) {
        super("Não foi possível encontrar um exame com o nome " + nome + ".");
    }

}
