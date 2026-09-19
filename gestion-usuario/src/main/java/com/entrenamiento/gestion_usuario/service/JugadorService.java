package com.entrenamiento.gestion_usuario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.entrenamiento.gestion_usuario.dto.JugadorRequestDTO;
import com.entrenamiento.gestion_usuario.dto.JugadorResponseDTO;
import com.entrenamiento.gestion_usuario.model.Jugador;
import com.entrenamiento.gestion_usuario.repository.JugadorRepository;

@Service
public class JugadorService {

    private final JugadorRepository jugadorRepository;

    public JugadorService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    public JugadorResponseDTO crearJugador(JugadorRequestDTO request) {

        Jugador jugador = new Jugador();

        jugador.setNombre(request.getNombre());
        jugador.setPosicion(request.getPosicion());

        Jugador guardado = jugadorRepository.save(jugador);

        return convertirDTO(guardado);
    }

    public List<JugadorResponseDTO> obtenerJugadores() {

        List<Jugador> jugadores = jugadorRepository.findAll();

        List<JugadorResponseDTO> respuesta = new ArrayList<>();

        for (Jugador jugador : jugadores) {
            respuesta.add(convertirDTO(jugador));
        }

        return respuesta;
    }

    private JugadorResponseDTO convertirDTO(Jugador jugador) {

        return new JugadorResponseDTO(
                jugador.getId(),
                jugador.getNombre(),
                jugador.getPosicion()
        );
    }
}
