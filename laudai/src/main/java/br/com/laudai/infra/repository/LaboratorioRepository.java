package br.com.laudai.infra.repository;

import br.com.laudai.domain.model.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Integer> {


}
