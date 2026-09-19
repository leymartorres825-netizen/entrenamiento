package com.entrenamiento.gestion_usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entrenamiento.gestion_usuario.model.Entrenamiento;

public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Long> {

    long countByNumero(Integer numero);
}
