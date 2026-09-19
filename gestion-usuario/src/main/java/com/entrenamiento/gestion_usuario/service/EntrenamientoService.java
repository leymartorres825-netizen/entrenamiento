package com.entrenamiento.gestion_usuario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.entrenamiento.gestion_usuario.dto.EquipoTitularDTO;
import com.entrenamiento.gestion_usuario.dto.EntrenamientoRequestDTO;
import com.entrenamiento.gestion_usuario.dto.EvaluacionDTO;
import com.entrenamiento.gestion_usuario.model.Entrenamiento;
import com.entrenamiento.gestion_usuario.model.EvaluacionEntrenamiento;
import com.entrenamiento.gestion_usuario.model.Jugador;
import com.entrenamiento.gestion_usuario.repository.EntrenamientoRepository;
import com.entrenamiento.gestion_usuario.repository.JugadorRepository;

@Service
public class EntrenamientoService {

    private final EntrenamientoRepository entrenamientoRepository;

    private final JugadorRepository jugadorRepository;

    public EntrenamientoService(
            EntrenamientoRepository entrenamientoRepository,
            JugadorRepository jugadorRepository) {

        this.entrenamientoRepository = entrenamientoRepository;
        this.jugadorRepository = jugadorRepository;
    }

    public Entrenamiento registrarEntrenamiento(
            EntrenamientoRequestDTO request) {

        if (request.getNumero() == null) {
            throw new RuntimeException("El número del entrenamiento es obligatorio");
        }

        if (request.getNumero() < 1 || request.getNumero() > 3) {
            throw new RuntimeException(
                    "Solo se permiten los entrenamientos 1, 2 y 3"
            );
        }

        long cantidad = entrenamientoRepository
                .countByNumero(request.getNumero());

        if (cantidad > 0) {
            throw new RuntimeException(
                    "El entrenamiento " + request.getNumero()
                            + " ya fue registrado"
            );
        }

        if (request.getEvaluaciones() == null
                || request.getEvaluaciones().isEmpty()) {

            throw new RuntimeException(
                    "Debe registrar al menos un jugador"
            );
        }

        Entrenamiento entrenamiento = new Entrenamiento();

        entrenamiento.setNumero(request.getNumero());
        entrenamiento.setFecha(request.getFecha());

        List<EvaluacionEntrenamiento> evaluaciones =
                new ArrayList<>();

        for (EvaluacionDTO dto : request.getEvaluaciones()) {

            if (dto.getJugadorId() == null) {
                throw new RuntimeException(
                        "El jugador es obligatorio"
                );
            }

            Jugador jugador = jugadorRepository
                    .findById(dto.getJugadorId())
                    .orElse(null);

            if (jugador == null) {
                throw new RuntimeException(
                        "No existe el jugador con id "
                                + dto.getJugadorId()
                );
            }

            validarPuntuacion(dto.getPotenciaTiro());
            validarPuntuacion(dto.getVelocidad());
            validarPuntuacion(dto.getPases());

            EvaluacionEntrenamiento evaluacion =
                    new EvaluacionEntrenamiento();

            evaluacion.setJugadorId(dto.getJugadorId());
            evaluacion.setPotenciaTiro(dto.getPotenciaTiro());
            evaluacion.setVelocidad(dto.getVelocidad());
            evaluacion.setPases(dto.getPases());

            evaluaciones.add(evaluacion);
        }

        entrenamiento.setEvaluaciones(evaluaciones);

        return entrenamientoRepository.save(entrenamiento);
    }

    private void validarPuntuacion(double valor) {

        if (valor < 0 || valor > 100) {
            throw new RuntimeException(
                    "Las puntuaciones deben estar entre 0 y 100"
            );
        }
    }

    public List<EquipoTitularDTO> obtenerEquipoTitular() {

        List<Entrenamiento> entrenamientos =
                entrenamientoRepository.findAll();

        if (entrenamientos.size() < 3) {

            throw new RuntimeException(
                    "Se necesitan 3 entrenamientos para calcular "
                            + "el equipo titular"
            );
        }

        List<Jugador> jugadores = jugadorRepository.findAll();

        List<Jugador> jugadoresValidos = new ArrayList<>();

        List<Double> promedios = new ArrayList<>();

        for (Jugador jugador : jugadores) {

            double suma = 0;

            int cantidadEntrenamientos = 0;

            for (Entrenamiento entrenamiento : entrenamientos) {

                boolean encontrado = false;

                for (EvaluacionEntrenamiento evaluacion :
                        entrenamiento.getEvaluaciones()) {

                    if (evaluacion.getJugadorId()
                            .equals(jugador.getId())) {

                        suma += evaluacion.calcularPuntuacion();

                        cantidadEntrenamientos++;

                        encontrado = true;

                        break;
                    }
                }

                if (!encontrado) {
                    break;
                }
            }

            if (cantidadEntrenamientos == 3) {

                double promedio = suma / 3;

                jugadoresValidos.add(jugador);

                promedios.add(promedio);
            }
        }

        if (jugadoresValidos.size() < 5) {

            throw new RuntimeException(
                    "No hay suficientes jugadores con "
                            + "3 entrenamientos registrados"
            );
        }

        ordenarJugadoresPorPromedio(
                jugadoresValidos,
                promedios
        );

        List<EquipoTitularDTO> equipo =
                new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            Jugador jugador = jugadoresValidos.get(i);

            double promedio = promedios.get(i);

            EquipoTitularDTO dto =
                    new EquipoTitularDTO(
                            jugador.getId(),
                            jugador.getNombre(),
                            jugador.getPosicion(),
                            promedio
                    );

            equipo.add(dto);
        }

        return equipo;
    }

    private void ordenarJugadoresPorPromedio(
            List<Jugador> jugadores,
            List<Double> promedios) {

        for (int i = 0; i < jugadores.size() - 1; i++) {

            for (int j = i + 1; j < jugadores.size(); j++) {

                if (promedios.get(j) > promedios.get(i)) {

                    Jugador jugadorTemporal =
                            jugadores.get(i);

                    jugadores.set(
                            i,
                            jugadores.get(j)
                    );

                    jugadores.set(
                            j,
                            jugadorTemporal
                    );

                    Double promedioTemporal =
                            promedios.get(i);

                    promedios.set(
                            i,
                            promedios.get(j)
                    );

                    promedios.set(
                            j,
                            promedioTemporal
                    );
                }
            }
        }
    }
}
