package mx.edu.utez.tarea04102026.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApuestaRequest {

    @NotNull(message = "El id del jugador es obligatorio")
    private Long idJugador;

    @NotNull(message = "La apuesta es obligatoria")
    private BigDecimal monto;
}