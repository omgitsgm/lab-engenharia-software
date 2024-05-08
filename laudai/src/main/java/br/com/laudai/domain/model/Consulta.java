package br.com.laudai.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Consulta {

    public Consulta() {}

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public LocalDateTime getDataHorario() {
        return dataHorario;
    }

    public void setDataHorario(LocalDateTime dataHorario) {
        this.dataHorario = dataHorario;
    }
}
