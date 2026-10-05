package mx.edu.utez.tarea04102026.service;

import mx.edu.utez.tarea04102026.controller.dto.CotizacionResponseDto;
import mx.edu.utez.tarea04102026.controller.dto.EnvioRequestDTO;
import mx.edu.utez.tarea04102026.exception.customExceptions.CustomBadRequestExceptions;
import org.springframework.stereotype.Service;

@Service
public class EnvioService {

    public CotizacionResponseDto calcularEnvio(EnvioRequestDTO data) {
        double volumen = data.getLargoCm() * data.getAnchoCm() * data.getAltoCm();

        if (data.getPesoKg() > 50) {
            throw new CustomBadRequestExceptions("El paquete no puede pesar más de 50 kg");
        }
        if (data.getLargoCm() > 150 || data.getAnchoCm() > 150 || data.getAltoCm() > 150) {
            throw new CustomBadRequestExceptions("Ninguna dimensión puede superar los 150 cm");
        }
        if (volumen > 1000000) {
            throw new CustomBadRequestExceptions("El volumen no puede superar los 1,000,000 cm³");
        }

        double costo = 80.0 + (data.getPesoKg() * 12.0);

        if (volumen > 50000) {
            costo += 100.0;
        }

        if ("EXPRESS".equalsIgnoreCase(data.getTipoEnvio())) {
            costo = costo * 1.40;
        } else if ("MISMO_DIA".equalsIgnoreCase(data.getTipoEnvio())) {
            costo = costo * 1.70;
        }

        if (data.getValorDeclarado() > 10000) {
            costo += data.getValorDeclarado() * 0.02;
        }

        CotizacionResponseDto respuesta = new CotizacionResponseDto();
        respuesta.setDetalle("Envío a CP " + data.getCodigoPostal());
        respuesta.setTotal(costo);

        return respuesta;
    }
}