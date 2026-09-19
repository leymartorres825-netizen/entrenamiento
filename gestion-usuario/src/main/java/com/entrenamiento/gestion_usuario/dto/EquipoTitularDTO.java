package com.entrenamiento.gestion_usuario.dto;

public class EquipoTitularDTO {

    private Long jugadorId;

    private String nombre;

    private String posicion;

    private double promedio;

    public EquipoTitularDTO() {
    }

    public EquipoTitularDTO(
            Long jugadorId,
            String nombre,
            String posicion,
            double promedio) {

        this.jugadorId = jugadorId;
        this.nombre = nombre;
        this.posicion = posicion;
        this.promedio = promedio;
    }

    public Long getJugadorId() {
        return jugadorId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public double getPromedio() {
        return promedio;
    }
}

