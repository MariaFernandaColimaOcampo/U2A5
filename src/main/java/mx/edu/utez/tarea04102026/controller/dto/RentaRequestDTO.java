package mx.edu.utez.tarea04102026.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RentaRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombreCliente;

    @Min(value = 1, message = "La edad debe ser mayor a 0")
    private int edadConductor;

    @NotBlank(message = "El tipo es obligatorio")
    private String tipoVehiculo;

    @Positive(message = "Los días de renta deben ser mayores a 0")
    private int diasRenta;

    @PositiveOrZero(message = "No pueden ser negativos")
    private int kilometrosEstimados;

    private boolean seguroCompleto;
}