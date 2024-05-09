package br.com.laudai.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Laboratorio {

    public Laboratorio(String nome) {
        this.nome = nome;
        this.consultas = new ArrayList<>();
        this.exames = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consulta> consultas;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exame> exames;

}
