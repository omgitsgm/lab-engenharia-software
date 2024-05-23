package br.com.laudai.infra.repository;

import br.com.laudai.domain.model.Radiologista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RadiologistaRepository extends JpaRepository<Radiologista, Integer> {

    Boolean existsByCrm(String crm);

    Optional<Radiologista> findByCrmAndSenha(String crm, String senha);

}
