package com.entrenamiento.gestion_usuario.dto;

public class JugadorResponseDTO {

    private Long id;

    private String nombre;

    private String posicion;

    public JugadorResponseDTO() {
    }

    public JugadorResponseDTO(Long id, String nombre, String posicion) {
        this.id = id;
        this.nombre = nombre;
        this.posicion = posicion;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPosicion() {
        return posicion;
    }
}
