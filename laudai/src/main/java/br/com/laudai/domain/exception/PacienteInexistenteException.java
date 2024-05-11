package br.com.laudai.domain.exception;

public class PacienteInexistenteException extends RuntimeException {

    public PacienteInexistenteException(Integer id) {
        super("Não foi possível encontrar um paciente com o id " + id + ".");
    }
}
