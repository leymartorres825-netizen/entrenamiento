package com.entrenamieto.gestion_usuario.repository;

import com.example.futbol.entity.Entrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntrenamientoRepository
        extends JpaRepository<Entrenamiento, Long> {

    Optional<Entrenamiento> findByNumeroEntrenamiento(
            int numeroEntrenamiento
    );
}