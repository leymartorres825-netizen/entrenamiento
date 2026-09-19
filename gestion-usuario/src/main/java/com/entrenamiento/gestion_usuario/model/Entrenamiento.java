package com.entrenamiento.gestion_usuario.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class Entrenamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;

    private LocalDate fecha;

    @ElementCollection
    @CollectionTable(
            name = "evaluaciones_entrenamiento",
            joinColumns = @JoinColumn(name = "entrenamiento_id")
    )
    private List<EvaluacionEntrenamiento> evaluaciones = new ArrayList<>();

    public Entrenamiento() {
    }

    public Entrenamiento(Integer numero, LocalDate fecha) {
        this.numero = numero;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
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

    public List<EvaluacionEntrenamiento> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<EvaluacionEntrenamiento> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }
}
