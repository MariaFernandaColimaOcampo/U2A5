package mx.edu.utez.tarea04102026.repository;

import mx.edu.utez.tarea04102026.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {

    // Método para buscar todas las partidas de un jugador específico
    List<Partida> findByJugadorId(Long jugadorId);
}