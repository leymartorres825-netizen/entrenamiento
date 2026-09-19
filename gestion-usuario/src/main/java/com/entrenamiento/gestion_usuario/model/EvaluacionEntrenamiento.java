package com.entrenamiento.gestion_usuario.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class EvaluacionEntrenamiento {

    private Long jugadorId;

    private double potenciaTiro;

    private double velocidad;

    private double pases;

    public EvaluacionEntrenamiento() {
    }

    public EvaluacionEntrenamiento(
            Long jugadorId,
            double potenciaTiro,
            double velocidad,
            double pases) {

        this.jugadorId = jugadorId;
        this.potenciaTiro = potenciaTiro;
        this.velocidad = velocidad;
        this.pases = pases;
    }

    public Long getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(Long jugadorId) {
        this.jugadorId = jugadorId;
    }

    public double getPotenciaTiro() {
        return potenciaTiro;
    }

    public void setPotenciaTiro(double potenciaTiro) {
        this.potenciaTiro = potenciaTiro;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public double getPases() {
        return pases;
    }

    public void setPases(double pases) {
        this.pases = pases;
    }

    public double calcularPuntuacion() {

        return (potenciaTiro * 0.20)
                + (velocidad * 0.30)
                + (pases * 0.50);
    }
}
