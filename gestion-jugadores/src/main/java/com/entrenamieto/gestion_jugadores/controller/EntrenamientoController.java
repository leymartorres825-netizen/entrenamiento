package com.entrenamieto.gestion_usuario.controller;

import com.entrenamieto.gestion_usuario.dto.request.EntrenamientoRequestDTO;
import com.entrenamieto.gestion_jugadores.service.EntrenamientoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EntrenamientoController {

    private final EntrenamientoService entrenamientoService;

    public EntrenamientoController(
            EntrenamientoService entrenamientoService) {

        this.entrenamientoService = entrenamientoService;
    }

    @PostMapping("/entrenamientos")
    public ResponseEntity<String> guardarEntrenamiento(
            @Valid @RequestBody EntrenamientoRequestDTO request) {

        String respuesta =
                entrenamientoService.guardarEntrenamiento(request);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/equipo-titular")
    public ResponseEntity<Object> obtenerEquipoTitular() {

        Object respuesta =
                entrenamientoService.obtenerEquipoTitular();

        return ResponseEntity.ok(respuesta);
    }
}