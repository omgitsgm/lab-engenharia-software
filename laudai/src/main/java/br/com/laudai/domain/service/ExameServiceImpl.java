package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Exame;
import br.com.laudai.infra.repository.ExameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExameServiceImpl implements ExameService {

    private final ExameRepository exameRepository;

    @Override
    public void cadastrar(String nome) {

        existsByNome(nome);

        Exame exame = new Exame(nome);
        exameRepository.save(exame);

    }

    @Override
    public Exame findById(Integer id) {
        // CRIAR EXCEPTION PARA CASO NÃO ENCONTRE O EXAME
        return exameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não existe um Exame com id " + id + "."));
    }

    @Override
    public Exame findByNome(String nome) {
        // Criar Exception
        return exameRepository.findByNomeEqualsIgnoreCase(nome)
                .orElseThrow(() -> new RuntimeException("Não existe um exame com o nome " + nome + "."));
    }

    @Override
    public void existsByNome(String nome) {
        if(exameRepository.existsByNome(nome))
            throw new RuntimeException("Já existe um exame com esse nome.");

    }
}
