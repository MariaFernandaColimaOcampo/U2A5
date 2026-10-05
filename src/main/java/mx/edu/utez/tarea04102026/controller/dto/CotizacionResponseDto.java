package mx.edu.utez.tarea04102026.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CotizacionResponseDto {

    private String detalle;
    private double total;
}