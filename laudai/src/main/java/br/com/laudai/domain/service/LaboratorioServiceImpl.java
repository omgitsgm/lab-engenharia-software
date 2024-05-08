package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Exame;
import br.com.laudai.domain.model.Laboratorio;
import br.com.laudai.infra.repository.ExameRepository;
import br.com.laudai.infra.repository.LaboratorioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratorioServiceImpl implements LaboratorioService{

    private final LaboratorioRepository laboratorioRepository;
    private final ExameService exameService;

    public LaboratorioServiceImpl(LaboratorioRepository laboratorioRepository, ExameService exameService) {
        this.laboratorioRepository = laboratorioRepository;
        this.exameService = exameService;
    }

    @Override
    public void save(Laboratorio laboratorio) {

        laboratorioRepository.save(laboratorio);

    }

    @Override
    public Laboratorio findById(Integer id) {
        // CRIAR EXCEPTION
        return laboratorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não existe um laboratório com o id " + id + "."));
    }

    @Override
    @Transactional
    public void adicionarExame(Integer laboratorioId, String nomeExame) {

        Laboratorio laboratorio = findById(laboratorioId);
        System.out.println("ID DO LABORATORIO = " + laboratorio.getId());

        Exame exame = exameService.findByNome(nomeExame);
        System.out.println("ID DO EXAME = " + exame.getId());

        System.out.println(exame.getLaboratorios());
        exame.getLaboratorios().add(laboratorio);
        System.out.println(exame.getLaboratorios());

        if(laboratorio.getExames().contains(exame)){
            throw new RuntimeException("Este exame já está adicionado na lista de exames do laboratório.");
        }

        laboratorio.getExames().add(exame);

    }

    @Override
    public List<Laboratorio> findAllByExame(String nome) {

        Exame exame = exameService.findByNome(nome);

        return exame.getLaboratorios();

    }
}
