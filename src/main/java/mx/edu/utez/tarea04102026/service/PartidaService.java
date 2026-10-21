package mx.edu.utez.tarea04102026.service;

import mx.edu.utez.tarea04102026.exception.customExceptions.BusinessException;
import mx.edu.utez.tarea04102026.model.EstadoPartida;
import mx.edu.utez.tarea04102026.model.Jugador;
import mx.edu.utez.tarea04102026.model.Partida;
import mx.edu.utez.tarea04102026.model.Tiro;
import mx.edu.utez.tarea04102026.repository.JugadorRepository;
import mx.edu.utez.tarea04102026.repository.PartidaRepository;
import mx.edu.utez.tarea04102026.repository.TiroRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class PartidaService {

    private final PartidaRepository partidaRepo;
    private final JugadorRepository jugadorRepo;
    private final TiroRepository tiroRepo;

    public PartidaService(PartidaRepository partidaRepo, JugadorRepository jugadorRepo, TiroRepository tiroRepo) {
        this.partidaRepo = partidaRepo;
        this.jugadorRepo = jugadorRepo;
        this.tiroRepo = tiroRepo;
    }

    public Partida iniciarPartida(Long idJugador, BigDecimal monto) {
        Jugador jugador = jugadorRepo.findById(idJugador).orElse(null);

        if (jugador == null) {
            throw new BusinessException("Jugador no encontrado");
        }

        if (jugador.getActivo() == false) {
            throw new BusinessException("El jugador esta inactivo");
        }

        if (jugador.getSaldo().compareTo(monto) < 0) {
            throw new BusinessException("Saldo insuficiente");
        }

        BigDecimal nuevoSaldo = jugador.getSaldo().subtract(monto);
        jugador.setSaldo(nuevoSaldo);
        jugadorRepo.save(jugador);

        Partida p = new Partida();
        p.setFecha(LocalDateTime.now());
        p.setEstado(EstadoPartida.EN_JUEGO);
        p.setJugador(jugador);
        p.setApuesta(monto);

        return partidaRepo.save(p);
    }

    public Tiro realizarTiro(Long idPartida) {
        Partida partida = partidaRepo.findById(idPartida).orElse(null);

        if (partida == null) {
            throw new BusinessException("La partida no existe");
        }

        if (partida.getEstado() == EstadoPartida.FINALIZADA) {
            throw new BusinessException("La partida ya finalizo");
        }

        Random rnd = new Random();
        int d1 = rnd.nextInt(6) + 1;
        int d2 = rnd.nextInt(6) + 1;
        int suma = d1 + d2;

        boolean gano = false;

        if (suma == 7 || suma == 11) {
            gano = true;
            partida.setEstado(EstadoPartida.FINALIZADA);

            Jugador j = partida.getJugador();
            BigDecimal premio = partida.getApuesta().multiply(new BigDecimal(2));
            j.setSaldo(j.getSaldo().add(premio));
            jugadorRepo.save(j);

        } else if (suma == 2 || suma == 3 || suma == 12) {
            partida.setEstado(EstadoPartida.FINALIZADA);
        }

        partidaRepo.save(partida);

        Tiro tiro = new Tiro();
        tiro.setPartida(partida);
        tiro.setDado1(d1);
        tiro.setDado2(d2);
        tiro.setSuma(suma);
        tiro.setGano(gano);

        return tiroRepo.save(tiro);
    }

    public Partida finalizarPartidaManual(Long idPartida) {
        Partida partida = partidaRepo.findById(idPartida).orElse(null);

        if (partida == null) {
            throw new BusinessException("Partida no encontrada");
        }

        partida.setEstado(EstadoPartida.FINALIZADA);
        return partidaRepo.save(partida);
    }

    public List<Partida> obtenerHistorialPartidas(Long idJugador) {
        return partidaRepo.findByJugadorId(idJugador);
    }
}