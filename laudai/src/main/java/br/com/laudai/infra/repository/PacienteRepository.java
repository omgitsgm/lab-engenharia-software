package br.com.laudai.infra.repository;

import br.com.laudai.domain.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    Boolean existsByCpf(String cpf);
    Boolean existsByEmail(String email);

    Optional<Paciente> findByEmailAndSenha(String email, String senha);

}
