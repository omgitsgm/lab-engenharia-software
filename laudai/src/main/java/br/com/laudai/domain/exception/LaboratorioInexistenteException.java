package br.com.laudai.domain.exception;

public class LaboratorioInexistenteException extends RuntimeException {

    public LaboratorioInexistenteException(Integer id) {
        super("Não foi possível encontrar um laboratório com o id " + id + ".");
    }
}
