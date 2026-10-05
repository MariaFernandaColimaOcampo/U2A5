package mx.edu.utez.tarea04102026.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HospedajeRequestDTO {

    @NotBlank(message = "es obligatorio")
    private String nombreHuesped;

    @NotBlank(message = "Es obligatorio")
    private String tipoHabitacion;

    @Positive(message = "debe ser mayor a 0")
    private int numeroNoches;

    @Positive(message = "El número debe ser mayor a 0")
    private int numeroHuespedes;

    @NotBlank(message = "La temporaba es obligatoria")
    private String temporada;

    private boolean incluyeDesayuno;
    private boolean incluyeEstacionamiento;
}