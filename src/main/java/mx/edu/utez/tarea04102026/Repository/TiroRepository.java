package mx.edu.utez.tarea04102026.repository;

import mx.edu.utez.tarea04102026.model.Tiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiroRepository extends JpaRepository<Tiro, Long> {
}