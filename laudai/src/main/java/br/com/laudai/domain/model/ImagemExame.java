package br.com.laudai.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ImagemExame {

    public ImagemExame(String nomeArquivo, String contentType, Long tamanho) {
        this.nomeArquivo = nomeArquivo;
        this.contentType = contentType;
        this.tamanho = tamanho;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nomeArquivo;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private Long tamanho;

    @OneToOne
    private ResultadoExame resultadoExame;

    @Override
    public String toString() {
        return "ImagemExame{" +
                "id=" + id +
                ", nomeArquivo='" + nomeArquivo + '\'' +
                ", contentType='" + contentType + '\'' +
                ", tamanho=" + tamanho +
                ", resultadoExameId=" + getResultadoExame().getId() +
                '}';
    }
}
