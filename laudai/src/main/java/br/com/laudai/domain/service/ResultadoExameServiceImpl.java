package br.com.laudai.domain.service;

import br.com.laudai.domain.model.Consulta;
import br.com.laudai.domain.model.ImagemExame;
import br.com.laudai.domain.model.ResultadoExame;
import br.com.laudai.infra.repository.ConsultaRepository;
import br.com.laudai.infra.repository.ImagemExameRepository;
import br.com.laudai.web.feign.model.ModeloResultadoInput;
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
    private final ModeloServiceImpl modeloService;

    @Transactional
    @Override
    public ImagemExame save(ImagemExame imagemExame, Integer idConsulta, InputStream inputStream) {

        Consulta consulta = consultaService.findById(idConsulta);

        if(consulta.getResultadoExame() == null) {

            log.info("Essa consulta ainda não estava relacionada a uma entidade ResultadoExame. Relacionando agora.");
            ResultadoExame resultadoExame = new ResultadoExame();
            consulta.setResultadoExame(resultadoExame);
            resultadoExame.setConsulta(consulta);

        } else if(consulta.getResultadoExame().getImagemExame() != null) {

            log.info("ResultadoExame da Consulta já possui uma imagem");
            log.debug(consulta.getResultadoExame().getImagemExame().toString());

            String nomeArquivoAntigo = consulta.getResultadoExame().getImagemExame().getNomeArquivo();

            Integer id = consulta.getResultadoExame().getImagemExame().getId();
            imagemExame.setId(id);

            imagemExame.setResultadoExame(consulta.getResultadoExame());

            log.info("Atualizando imagem no banco de dados...");
            imagemExameRepository.save(imagemExame);

            log.info(consulta.getResultadoExame().getImagemExame().toString());

            log.info("Substituindo imagem antiga pela nova no armazenamento local...");
            imagemStorageService.substituir(nomeArquivoAntigo, imagemExame.getNomeArquivo(), inputStream);

            // Eu preciso processar a imagem que foi salva e salvar a porcentagem de ter alzheimer em resultadoExame
            ModeloResultadoInput predict = modeloService.predict(imagemExame.getNomeArquivo());
            consulta.getResultadoExame().setProbabilidade(predict.prediction());

            return imagemExame;

        }

        log.info("Estabelecendo relacionamentos entre entidades Consulta, ResultadoExame e ImagemExame...");
        // Pego o ResultadoExame da Consulta e defino a ImagemExame.
        consulta.getResultadoExame().setImagemExame(imagemExame);
        // Faço o contrário também...
        imagemExame.setResultadoExame(consulta.getResultadoExame());

        // Usando cascade eu consigo atualizar todos os objetos dentro de consulta só persistindo Consulta
        log.info("Armazenando imagem no banco de dados...");
        consultaRepository.save(consulta);
        log.info("Armazenando imagem localmente...");
        imagemStorageService.armazenar(imagemExame.getNomeArquivo(), inputStream);

        // Eu preciso processar a imagem que foi salva e salvar a porcentagem de ter alzheimer em resultadoExame
        ModeloResultadoInput predict = modeloService.predict(imagemExame.getNomeArquivo());
        consulta.getResultadoExame().setProbabilidade(predict.prediction());

        return consulta.getResultadoExame().getImagemExame();

    }

    @Override
    public ImagemExame buscar(Integer idConsulta) {
        Consulta consulta = consultaService.findById(idConsulta);

        log.info("Checando se existe uma imagem de resultado para essa consulta...");
        if(consulta.getResultadoExame() != null && consulta.getResultadoExame().getImagemExame() != null)
                return consulta.getResultadoExame().getImagemExame();

        log.error("Não existe uma imagem de resultado para essa consulta.");
        throw new RuntimeException("Não existe uma imagem Exame para essa Consulta");
    }
}
