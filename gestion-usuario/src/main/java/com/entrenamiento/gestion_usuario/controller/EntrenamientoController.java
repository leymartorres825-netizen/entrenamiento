package com.entrenamiento.gestion_usuario.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entrenamiento.gestion_usuario.dto.EquipoTitularDTO;
import com.entrenamiento.gestion_usuario.dto.EntrenamientoRequestDTO;
import com.entrenamiento.gestion_usuario.model.Entrenamiento;
import com.entrenamiento.gestion_usuario.service.EntrenamientoService;

@RestController
@RequestMapping("/api/entrenamientos")
public class EntrenamientoController {

    private final EntrenamientoService entrenamientoService;

    public EntrenamientoController(
            EntrenamientoService entrenamientoService) {

        this.entrenamientoService = entrenamientoService;
    }

    @PostMapping
    public ResponseEntity<Entrenamiento> registrarEntrenamiento(
            @RequestBody EntrenamientoRequestDTO request) {

        return ResponseEntity.ok(
                entrenamientoService.registrarEntrenamiento(request)
        );
    }

    @GetMapping("/equipo-titular")
    public ResponseEntity<List<EquipoTitularDTO>> obtenerEquipoTitular() {

        return ResponseEntity.ok(
                entrenamientoService.obtenerEquipoTitular()
        );
    }
}
