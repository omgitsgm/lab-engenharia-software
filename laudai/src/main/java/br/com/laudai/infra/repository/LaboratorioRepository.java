package br.com.laudai.infra.repository;

import br.com.laudai.domain.model.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Integer> {
}
