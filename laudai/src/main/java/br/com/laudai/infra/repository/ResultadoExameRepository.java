package br.com.laudai.infra.repository;

import br.com.laudai.domain.model.ResultadoExame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultadoExameRepository extends JpaRepository<ResultadoExame, Integer> {
}
