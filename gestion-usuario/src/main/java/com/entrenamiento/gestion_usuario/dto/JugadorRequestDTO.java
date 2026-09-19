package com.entrenamiento.gestion_usuario.dto;

public class JugadorRequestDTO {

    private String nombre;

    private String posicion;

    public JugadorRequestDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
}
