package com.entrenamieto.gestion_usuario.dto.response;

import java.util.List;

public class EquipoTitularResponseDTO {

    private List<JugadorTitularResponseDTO> titulares;

    public EquipoTitularResponseDTO() {
    }

    public EquipoTitularResponseDTO(
            List<JugadorTitularResponseDTO> titulares) {

        this.titulares = titulares;
    }

    public List<JugadorTitularResponseDTO> getTitulares() {
        return titulares;
    }

    public void setTitulares(
            List<JugadorTitularResponseDTO> titulares) {

        this.titulares = titulares;
    }
}