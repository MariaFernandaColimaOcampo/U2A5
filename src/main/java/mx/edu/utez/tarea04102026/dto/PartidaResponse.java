package mx.edu.utez.tarea04102026.dto;

import mx.edu.utez.tarea04102026.model.EstadoPartida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidaResponse {
    private Long id;
    private LocalDateTime fecha;
    private EstadoPartida estado;
    private Long idJugador;
    private BigDecimal apuesta;
}