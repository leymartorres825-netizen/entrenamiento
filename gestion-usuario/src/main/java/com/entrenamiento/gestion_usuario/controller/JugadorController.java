package com.entrenamiento.gestion_usuario.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entrenamiento.gestion_usuario.dto.JugadorRequestDTO;
import com.entrenamiento.gestion_usuario.dto.JugadorResponseDTO;
import com.entrenamiento.gestion_usuario.service.JugadorService;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @PostMapping
    public ResponseEntity<JugadorResponseDTO> crearJugador(
            @RequestBody JugadorRequestDTO request) {

        return ResponseEntity.ok(
                jugadorService.crearJugador(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<JugadorResponseDTO>> obtenerJugadores() {

        return ResponseEntity.ok(
                jugadorService.obtenerJugadores()
        );
    }
}
