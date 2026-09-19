package com.entrenamiento.gestion_usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entrenamiento.gestion_usuario.model.Jugador;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {
}
