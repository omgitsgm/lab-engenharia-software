package br.com.laudai.domain.exception;

public class ExameDuplicadoException extends RuntimeException {

    public ExameDuplicadoException(String nome) {
        super("Já existe um exame com o nome " + nome + ".");
    }

    public ExameDuplicadoException(String nomeExame, String nomeLaboratorio) {
        super("O exame " + nomeExame + " já está disponível no laboratório " + nomeLaboratorio + ".");
    }
}
