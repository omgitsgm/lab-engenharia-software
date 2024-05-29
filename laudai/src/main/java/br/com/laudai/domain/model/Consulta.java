package br.com.laudai.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Consulta {

    public Consulta(Paciente paciente, Exame exame, Laboratorio laboratorio, LocalDateTime dataHorario) {
        this.paciente = paciente;
        this.exame = exame;
        this.laboratorio = laboratorio;
        this.dataHorario = dataHorario;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Exame exame;

    @ManyToOne
    private Laboratorio laboratorio;

    private LocalDateTime dataHorario;

    @ManyToOne
    private Radiologista radiologista;

    @OneToOne(cascade = CascadeType.ALL)
    private ResultadoExame resultadoExame;

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", exame=" + exame +
                ", laboratorio=" + laboratorio +
                ", dataHorario=" + dataHorario +
                ", radiologista=" + radiologista +
                ", resultadoExame=" + resultadoExame +
                '}';
    }

}
