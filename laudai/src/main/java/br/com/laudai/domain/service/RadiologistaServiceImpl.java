package br.com.laudai.domain.service;

import br.com.laudai.domain.exception.AutenticacaoException;
import br.com.laudai.domain.exception.RadiologistaCrmDuplicadoException;
import br.com.laudai.domain.model.Radiologista;
import br.com.laudai.infra.repository.RadiologistaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RadiologistaServiceImpl implements RadiologistaService{

    private final RadiologistaRepository radiologistaRepository;

    @Override
    public Radiologista save(Radiologista radiologista) {

        log.info("Checando se já existe um radiologista cadastrado com o mesmo CRM...");
        // Verificar se já existe Radiologista com esse mesmo CRM
        if(radiologistaRepository.existsByCrm(radiologista.getCrm()).equals(Boolean.TRUE)) {
            log.error("Já existe um radiologista cadastrado com o mesmo CRM.");
            throw new RadiologistaCrmDuplicadoException(radiologista.getCrm());
        }

        log.info("Persistindo radiologista...");
        return radiologistaRepository.save(radiologista);

    }

    @Override
    public Radiologista autenticar(String crm, String senha) {
        log.info("Checando credenciais do radiologista...");
        return radiologistaRepository.findByCrmAndSenha(crm, senha)
                .orElseThrow(() -> {
                    log.info("Credenciais inválidas.");
                    return new AutenticacaoException();
                });

    }
}
