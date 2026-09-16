package com.entrenamieto.gestion_usuario.repository;

import com.entrenamieto.gestion_usuario.entity.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {

}