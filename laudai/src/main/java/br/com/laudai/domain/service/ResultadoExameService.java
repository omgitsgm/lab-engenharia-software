package br.com.laudai.domain.service;

import br.com.laudai.domain.model.ImagemExame;

import java.io.InputStream;

public interface ResultadoExameService {

    public ImagemExame save(ImagemExame imagemExame, Integer idConsulta, InputStream inputStream);

}
