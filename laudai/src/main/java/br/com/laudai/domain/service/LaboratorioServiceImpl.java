package br.com.laudai.domain.service;

import br.com.laudai.domain.exception.ExameDuplicadoException;
import br.com.laudai.domain.exception.LaboratorioInexistenteException;
import br.com.laudai.domain.model.Exame;
import br.com.laudai.domain.model.Laboratorio;
import br.com.laudai.infra.repository.ExameRepository;
import br.com.laudai.infra.repository.LaboratorioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LaboratorioServiceImpl implements LaboratorioService{

    private final LaboratorioRepository laboratorioRepository;
    private final ExameService exameService;

    @Override
    public void save(Laboratorio laboratorio) {

        laboratorioRepository.save(laboratorio);

    }

    @Override
    public Laboratorio findById(Integer id) {
        return laboratorioRepository.findById(id)
                .orElseThrow(() -> new LaboratorioInexistenteException(id));
    }

    @Override
    @Transactional
    public void adicionarExame(Integer laboratorioId, String nomeExame) {

        Laboratorio laboratorio = findById(laboratorioId);

        Exame exame = exameService.findByNome(nomeExame);

        exame.getLaboratorios().add(laboratorio);

        if(laboratorio.getExames().contains(exame))
            throw new ExameDuplicadoException(nomeExame, laboratorio.getNome());

        laboratorio.getExames().add(exame);

    }

    @Override
    public List<Laboratorio> findAllByExame(String nome) {

        Exame exame = exameService.findByNome(nome);

        return exame.getLaboratorios();

    }
}
