package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Consulta;
import br.com.laudai.domain.model.ImagemExame;
import br.com.laudai.domain.model.ResultadoExame;
import br.com.laudai.infra.repository.ConsultaRepository;
import br.com.laudai.infra.repository.ImagemExameRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Slf4j
@RequiredArgsConstructor
@Service
public class ResultadoExameServiceImpl implements ResultadoExameService {

    private final ConsultaService consultaService;
    private final ConsultaRepository consultaRepository;
    private final ImagemExameRepository imagemExameRepository;
    private final ImagemStorageService imagemStorageService;

    @Transactional
    @Override
    public ImagemExame save(ImagemExame imagemExame, Integer idConsulta, InputStream inputStream) {

        /*
         * Para persistir uma ImagemExame, eu preciso de um ResultadoExame.
         * Para ter um ResultadoExame, eu preciso de uma Consulta.
         */

        // Procuro pela consulta
        Consulta consulta = consultaService.findById(idConsulta);

        // Nem toda consulta possui um ResultadoExame, então...
        if(consulta.getResultadoExame() == null) {
            ResultadoExame resultadoExame = new ResultadoExame();
            consulta.setResultadoExame(resultadoExame);
            resultadoExame.setConsulta(consulta);
        } else if(consulta.getResultadoExame().getImagemExame() != null) {
            // Se já tem uma imagem, eu atualizo a imagem que já estava persistida.
            log.info("Já tem uma imagem!");
            log.info(consulta.getResultadoExame().getImagemExame().toString());
            String nomeArquivoAntigo = consulta.getResultadoExame().getImagemExame().getNomeArquivo();

            Integer id = consulta.getResultadoExame().getImagemExame().getId();
            imagemExame.setId(id);

            imagemExame.setResultadoExame(consulta.getResultadoExame());

            imagemExameRepository.save(imagemExame);

            log.info(consulta.getResultadoExame().getImagemExame().toString());


            imagemStorageService.substituir(nomeArquivoAntigo, imagemExame.getNomeArquivo(), inputStream);

            return imagemExame;

        }

        // Pego o ResultadoExame da Consulta e defino a ImagemExame.
        consulta.getResultadoExame().setImagemExame(imagemExame);
        // Faço o contrário também...
        imagemExame.setResultadoExame(consulta.getResultadoExame());

        // Usando cascade eu consigo atualizar todos os objetos dentro de consulta só persistindo Consulta
        consultaRepository.save(consulta);
        imagemStorageService.armazenar(imagemExame.getNomeArquivo(), inputStream);

        return consulta.getResultadoExame().getImagemExame();

    }
}
