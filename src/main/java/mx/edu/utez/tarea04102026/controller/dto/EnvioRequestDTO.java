package mx.edu.utez.tarea04102026.controller.dto;

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
public class EnvioRequestDTO {

    @NotBlank(message = "El código postal es obligatorio")
    private String codigoPostal;

    @Positive(message = "El peso debe ser mayor a 0")
    private double pesoKg;

    @Positive(message = "El largo debe ser mayor a 0")
    private double largoCm;

    @Positive(message = "El ancho debe ser mayor a 0")
    private double anchoCm;

    @Positive(message = "El alto debe ser mayor a 0")
    private double altoCm;

    @NotBlank(message = "El tipo de envío es obligatorio")
    private String tipoEnvio;

    @PositiveOrZero(message = "El valor declarado no puede ser negativo")
    private double valorDeclarado;
}