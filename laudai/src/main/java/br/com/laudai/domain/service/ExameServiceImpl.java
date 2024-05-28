package br.com.laudai.domain.service;

import br.com.laudai.domain.exception.ExameDuplicadoException;
import br.com.laudai.domain.exception.ExameInexistenteException;
import br.com.laudai.domain.model.Exame;
import br.com.laudai.infra.repository.ExameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExameServiceImpl implements ExameService {

    private final ExameRepository exameRepository;

    @Override
    public Exame cadastrar(String nome) {

        log.info("Checando se já existe um exame cadastrado com o mesmo nome.");
        if(exameRepository.existsByNome(nome).equals(Boolean.TRUE)) {
            log.error("Já existe um exame cadastrado com o mesmo nome.");
            throw new ExameDuplicadoException(nome);
        }

        Exame exame = new Exame(nome);

        log.info("Persistindo exame...");
        return exameRepository.save(exame);

    }

    @Override
    public Exame findById(Integer id) {
        log.info("Procurando pelo exame...");
        return exameRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Exame não encontrado.");
                    return new ExameInexistenteException(id);
                });
    }

    @Override
    public Exame findByNome(String nome) {
        log.info("Procurando pelo exame...");
        return exameRepository.findByNomeEqualsIgnoreCase(nome)
                .orElseThrow(() -> {
                    log.error("Exame não encontrado.");
                    return new ExameInexistenteException(nome);
                });
    }

    @Override
    public List<Exame> findAll() {
        log.info("Procurando por todos os exames...");
        return exameRepository.findAll();
    }

}
