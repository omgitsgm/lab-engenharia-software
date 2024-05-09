package br.com.laudai.infra.repository;

import br.com.laudai.domain.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    void deleteByCpf(String cpf);

}