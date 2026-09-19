package com.entrenamiento.gestion_usuario.dto;

public class EvaluacionDTO {

    private Long jugadorId;

    private double potenciaTiro;

    private double velocidad;

    private double pases;

    public EvaluacionDTO() {
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
}