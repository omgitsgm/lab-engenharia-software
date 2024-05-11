package br.com.laudai.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consulta> consultas;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exame> exames;

}
