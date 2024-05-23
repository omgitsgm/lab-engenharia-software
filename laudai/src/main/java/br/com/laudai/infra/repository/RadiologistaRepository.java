package br.com.laudai.infra.repository;

import br.com.laudai.domain.model.Radiologista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RadiologistaRepository extends JpaRepository<Radiologista, Integer> {

    Boolean existsByCrm(String crm);

}
