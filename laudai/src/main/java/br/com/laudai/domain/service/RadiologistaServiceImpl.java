package br.com.laudai.domain.service;

import br.com.laudai.domain.exception.AutenticacaoException;
import br.com.laudai.domain.exception.RadiologistaCrmDuplicadoException;
import br.com.laudai.domain.model.Radiologista;
import br.com.laudai.infra.repository.RadiologistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RadiologistaServiceImpl implements RadiologistaService{

    private final RadiologistaRepository radiologistaRepository;

    @Override
    public Radiologista save(Radiologista radiologista) {

        // Verificar se já existe Radiologista com esse mesmo CRM
        if(radiologistaRepository.existsByCrm(radiologista.getCrm()).equals(Boolean.TRUE))
            throw new RadiologistaCrmDuplicadoException(radiologista.getCrm());

        return radiologistaRepository.save(radiologista);

    }

    @Override
    public Radiologista autenticar(String crm, String senha) {

        return radiologistaRepository.findByCrmAndSenha(crm, senha)
                .orElseThrow(AutenticacaoException::new);

    }
}
