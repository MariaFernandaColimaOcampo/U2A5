package mx.edu.utez.tarea04102026.service;

import mx.edu.utez.tarea04102026.exception.customExceptions.BusinessException;
import mx.edu.utez.tarea04102026.model.Jugador;
import mx.edu.utez.tarea04102026.repository.JugadorRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class JugadorService {

    private final JugadorRepository jugadorRepo;

    // Inyección por constructor
    public JugadorService(JugadorRepository jugadorRepo) {
        this.jugadorRepo = jugadorRepo;
    }

    public Jugador crearJugador(Jugador jugador) {
        if (jugador.getSaldo() == null) {
            jugador.setSaldo(BigDecimal.ZERO);
        }
        if (jugador.getActivo() == null) {
            jugador.setActivo(true);
        }
        return jugadorRepo.save(jugador);
    }

    public Jugador recargarSaldo(Long idJugador, BigDecimal monto) {
        Jugador jugador = jugadorRepo.findById(idJugador).orElse(null);

        if (jugador == null) {
            throw new BusinessException("Jugador no encontrado");
        }

        if (jugador.getActivo() == false) {
            throw new BusinessException("El jugador esta inactivo");
        }

        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("El monto a recargar debe ser mayor a cero");
        }

        BigDecimal nuevoSaldo = jugador.getSaldo().add(monto);
        jugador.setSaldo(nuevoSaldo);

        return jugadorRepo.save(jugador);
    }

    public List<Jugador> obtenerTodos() {
        return jugadorRepo.findAll();
    }

    public Jugador obtenerPorId(Long id) {
        Jugador jugador = jugadorRepo.findById(id).orElse(null);
        if (jugador == null) {
            throw new BusinessException("Jugador no encontrado");
        }
        return jugador;
    }
}