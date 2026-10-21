package mx.edu.utez.tarea04102026.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RecargaRequest {

    @NotNull(message = "El monto es obligatorio")
    private BigDecimal monto;

    public RecargaRequest() {
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}