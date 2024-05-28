package br.com.laudai.domain.service;

import br.com.laudai.web.feign.model.ModeloResultadoInput;

public interface ModeloService {

     ModeloResultadoInput predict(String nomeArquivo);

}
