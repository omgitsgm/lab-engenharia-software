package br.com.laudai.domain.service;

import br.com.laudai.domain.exception.ExameDuplicadoException;
import br.com.laudai.domain.exception.ExameInexistenteException;
import br.com.laudai.domain.model.Exame;
import br.com.laudai.infra.repository.ExameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExameServiceImpl implements ExameService {

    private final ExameRepository exameRepository;

    @Override
    public Exame cadastrar(String nome) {

        if(exameRepository.existsByNome(nome).equals(Boolean.TRUE))
            throw new ExameDuplicadoException(nome);

        Exame exame = new Exame(nome);

        return exameRepository.save(exame);

    }

    @Override
    public Exame findById(Integer id) {
        return exameRepository.findById(id)
                .orElseThrow(() -> new ExameInexistenteException(id));
    }

    @Override
    public Exame findByNome(String nome) {
        return exameRepository.findByNomeEqualsIgnoreCase(nome)
                .orElseThrow(() -> new ExameInexistenteException(nome));
    }

}
