package mx.edu.utez.tarea04102026.repository;

import mx.edu.utez.tarea04102026.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
}