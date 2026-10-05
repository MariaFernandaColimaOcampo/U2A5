package mx.edu.utez.tarea04102026.service;

import mx.edu.utez.tarea04102026.controller.dto.CotizacionResponseDto;
import mx.edu.utez.tarea04102026.controller.dto.HospedajeRequestDTO;
import mx.edu.utez.tarea04102026.exception.customExceptions.CustomBadRequestExceptions;
import org.springframework.stereotype.Service;

@Service
public class HospedajeService {

    public CotizacionResponseDto calcularHospedaje(HospedajeRequestDTO data) {
        if (data.getNumeroNoches() > 30) {
            throw new CustomBadRequestExceptions("El número de noches no puede superar los 30");
        }

        String hab = data.getTipoHabitacion().toUpperCase();
        int capacidadMax = 0;
        double costoNoche = 0.0;

        switch (hab) {
            case "INDIVIDUAL":
                capacidadMax = 1;
                costoNoche = 700.0;
                break;
            case "DOBLE":
                capacidadMax = 2;
                costoNoche = 1100.0;
                break;
            case "SUITE":
                capacidadMax = 4;
                costoNoche = 1800.0;
                break;
            default:
                throw new CustomBadRequestExceptions("Tipo de habitación no válido");
        }

        if (data.getNumeroHuespedes() > capacidadMax) {
            throw new CustomBadRequestExceptions("Capacidad excedida: La habitación " + hab + " admite hasta " + capacidadMax + " huésped(es)");
        }

        double costoHospedaje = costoNoche * data.getNumeroNoches();

        if ("BAJA".equalsIgnoreCase(data.getTemporada())) {
            costoHospedaje = costoHospedaje * 0.90;
        } else if ("ALTA".equalsIgnoreCase(data.getTemporada())) {
            costoHospedaje = costoHospedaje * 1.25;
        }

        if (data.getNumeroNoches() >= 7) {
            costoHospedaje = costoHospedaje * 0.92;
        }

        double costoDesayuno = 0.0;
        if (data.isIncluyeDesayuno()) {
            costoDesayuno = data.getNumeroHuespedes() * data.getNumeroNoches() * 150.0;
        }

        double costoEstacionamiento = 0.0;
        if (data.isIncluyeEstacionamiento()) {
            costoEstacionamiento = data.getNumeroNoches() * 100.0;
        }

        double subtotal = costoHospedaje + costoDesayuno + costoEstacionamiento;
        double impuesto = subtotal * 0.04;
        double total = subtotal + impuesto;

        CotizacionResponseDto respuesta = new CotizacionResponseDto();
        respuesta.setDetalle("Hospedaje a nombre de " + data.getNombreHuesped());
        respuesta.setTotal(total);

        return respuesta;
    }
}