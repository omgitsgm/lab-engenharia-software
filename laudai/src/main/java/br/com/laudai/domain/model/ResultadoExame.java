package br.com.laudai.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ResultadoExame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Consulta consulta;

    @OneToOne(cascade = CascadeType.ALL)
    private ImagemExame imagemExame;

    private Double probabilidade;

}
