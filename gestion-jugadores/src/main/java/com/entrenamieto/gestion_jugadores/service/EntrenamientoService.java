package com.entrenamieto.gestion_usuario.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.entrenamieto.gestion_usuario.dto.request.EntrenamientoRequestDTO;
import com.entrenamieto.gestion_usuario.dto.request.JugadorRequestDTO;
import com.entrenamieto.gestion_usuario.dto.response.EquipoTitularResponseDTO;
import com.entrenamieto.gestion_usuario.dto.response.JugadorTitularResponseDTO;
import com.entrenamieto.gestion_usuario.entity.Entrenamiento;
import com.entrenamieto.gestion_usuario.entity.Jugador;
import com.entrenamieto.gestion_usuario.repository.EntrenamientoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntrenamientoService {

    private final EntrenamientoRepository entrenamientoRepository;

    /**
     * Método para guardar un entrenamiento.
     *
     * @param request información del entrenamiento
     * @return mensaje de confirmación
     */
    public String guardarEntrenamiento(EntrenamientoRequestDTO request) {

        Optional<Entrenamiento> entrenamientoEncontrado =
                entrenamientoRepository.findByNumeroEntrenamiento(
                        request.getNumeroEntrenamiento()
                );

        Entrenamiento entrenamiento;

        if (entrenamientoEncontrado.isPresent()) {

            entrenamiento = entrenamientoEncontrado.get();

            entrenamiento.getJugadores().clear();

        } else {

            entrenamiento = new Entrenamiento();

            entrenamiento.setNumeroEntrenamiento(
                    request.getNumeroEntrenamiento()
            );
        }

        List<JugadorRequestDTO> jugadores =
                request.getJugadores();

        for (JugadorRequestDTO jugadorRequest : jugadores) {

            Jugador jugador = new Jugador();

            jugador.setNombre(jugadorRequest.getNombre());
            jugador.setPotenciaTiro(
                    jugadorRequest.getPotenciaTiro()
            );
            jugador.setVelocidad(
                    jugadorRequest.getVelocidad()
            );
            jugador.setPases(
                    jugadorRequest.getPases()
            );

            double resultado =
                    (jugador.getPotenciaTiro() * 0.20)
                    + (jugador.getVelocidad() * 0.30)
                    + (jugador.getPases() * 0.50);

            jugador.setResultado(resultado);

            jugador.setEntrenamiento(entrenamiento);

            entrenamiento.getJugadores().add(jugador);
        }

        entrenamientoRepository.save(entrenamiento);

        return "Entrenamiento almacenado correctamente";
    }

    /**
     * Método para obtener los 5 jugadores titulares.
     *
     * @return equipo titular o mensaje si faltan entrenamientos
     */
    public Object obtenerEquipoTitular() {

        List<Entrenamiento> entrenamientos =
                entrenamientoRepository.findAll();

        boolean tieneEntrenamiento1 = false;
        boolean tieneEntrenamiento2 = false;
        boolean tieneEntrenamiento3 = false;

        for (Entrenamiento entrenamiento : entrenamientos) {

            if (entrenamiento.getNumeroEntrenamiento() == 1) {
                tieneEntrenamiento1 = true;
            }

            if (entrenamiento.getNumeroEntrenamiento() == 2) {
                tieneEntrenamiento2 = true;
            }

            if (entrenamiento.getNumeroEntrenamiento() == 3) {
                tieneEntrenamiento3 = true;
            }
        }

        if (!tieneEntrenamiento1
                || !tieneEntrenamiento2
                || !tieneEntrenamiento3) {

            return "No hay suficiente información. "
                    + "Se requieren los 3 entrenamientos de la semana.";
        }

        Entrenamiento entrenamiento1 = null;
        Entrenamiento entrenamiento2 = null;
        Entrenamiento entrenamiento3 = null;

        for (Entrenamiento entrenamiento : entrenamientos) {

            if (entrenamiento.getNumeroEntrenamiento() == 1) {
                entrenamiento1 = entrenamiento;
            }

            if (entrenamiento.getNumeroEntrenamiento() == 2) {
                entrenamiento2 = entrenamiento;
            }

            if (entrenamiento.getNumeroEntrenamiento() == 3) {
                entrenamiento3 = entrenamiento;
            }
        }

        List<JugadorTitularResponseDTO> jugadores =
                new ArrayList<>();

        for (Jugador jugador1 : entrenamiento1.getJugadores()) {

            Jugador jugador2 = buscarJugador(
                    entrenamiento2.getJugadores(),
                    jugador1.getNombre()
            );

            Jugador jugador3 = buscarJugador(
                    entrenamiento3.getJugadores(),
                    jugador1.getNombre()
            );

            if (jugador2 != null && jugador3 != null) {

                double promedio =
                        (jugador1.getResultado()
                        + jugador2.getResultado()
                        + jugador3.getResultado()) / 3;

                JugadorTitularResponseDTO jugadorResponse =
                        new JugadorTitularResponseDTO();

                jugadorResponse.setJugador(
                        jugador1.getNombre()
                );

                jugadorResponse.setPuntuacion(
                        promedio
                );

                jugadores.add(jugadorResponse);
            }
        }

        ordenarJugadores(jugadores);

        EquipoTitularResponseDTO equipo =
                new EquipoTitularResponseDTO();

        List<JugadorTitularResponseDTO> titulares =
                new ArrayList<>();

        for (int i = 0; i < 5 && i < jugadores.size(); i++) {

            titulares.add(jugadores.get(i));
        }

        equipo.setTitulares(titulares);

        return equipo;
    }

    /**
     * Busca un jugador por su nombre.
     */
    private Jugador buscarJugador(
            List<Jugador> jugadores,
            String nombre) {

        for (Jugador jugador : jugadores) {

            if (jugador.getNombre().equalsIgnoreCase(nombre)) {

                return jugador;
            }
        }

        return null;
    }

    /**
     * Ordena los jugadores de mayor a menor puntuación.
     */
    private void ordenarJugadores(
            List<JugadorTitularResponseDTO> jugadores) {

        for (int i = 0; i < jugadores.size() - 1; i++) {

            for (int j = 0;
                 j < jugadores.size() - 1 - i;
                 j++) {

                if (jugadores.get(j).getPuntuacion()
                        < jugadores.get(j + 1).getPuntuacion()) {

                    JugadorTitularResponseDTO temporal =
                            jugadores.get(j);

                    jugadores.set(
                            j,
                            jugadores.get(j + 1)
                    );

                    jugadores.set(
                            j + 1,
                            temporal
                    );
                }
            }
        }
    }
}