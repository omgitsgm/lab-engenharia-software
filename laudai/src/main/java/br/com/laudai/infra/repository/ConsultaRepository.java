package br.com.laudai.infra.repository;

import br.com.laudai.domain.model.Consulta;
import br.com.laudai.domain.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

    List<Consulta> findAllByPaciente(Paciente paciente);

}
