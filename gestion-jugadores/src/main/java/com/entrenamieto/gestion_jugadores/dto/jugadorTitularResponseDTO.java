package com.entrenamieto.gestion_usuario.dto.response;

public class JugadorTitularResponseDTO {

    private String jugador;
    private double puntuacion;

    public JugadorTitularResponseDTO() {
    }

    public JugadorTitularResponseDTO(
            String jugador,
            double puntuacion) {

        this.jugador = jugador;
        this.puntuacion = puntuacion;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }
}