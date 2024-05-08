package br.com.laudai.infra.repository;

import br.com.laudai.domain.model.Exame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExameRepository extends JpaRepository<Exame, Integer> {

    Optional<Exame> findByNomeIgnoreCase(String nome);
    Optional<Exame> findByNomeEqualsIgnoreCase(String nome);

    Boolean existsByNome(String nome);

}
