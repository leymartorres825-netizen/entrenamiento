package com.entrenamieto.gestion_usuario.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class EntrenamientoRequestDTO {

    @Min(value = 1, message = "El entrenamiento debe ser 1, 2 o 3")
    @Max(value = 3, message = "El entrenamiento debe ser 1, 2 o 3")
    private int numeroEntrenamiento;

    @NotEmpty(message = "Debe enviar los jugadores")
    @Valid
    private List<JugadorRequestDTO> jugadores;

    public EntrenamientoRequestDTO() {
    }

    public int getNumeroEntrenamiento() {
        return numeroEntrenamiento;
    }

    public void setNumeroEntrenamiento(int numeroEntrenamiento) {
        this.numeroEntrenamiento = numeroEntrenamiento;
    }

    public List<JugadorRequestDTO> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<JugadorRequestDTO> jugadores) {
        this.jugadores = jugadores;
    }
}