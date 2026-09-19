package com.entrenamiento.gestion_usuario.dto;

import java.time.LocalDate;
import java.util.List;

public class EntrenamientoRequestDTO {

    private Integer numero;

    private LocalDate fecha;

    private List<EvaluacionDTO> evaluaciones;

    public EntrenamientoRequestDTO() {
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<EvaluacionDTO> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<EvaluacionDTO> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }
}
