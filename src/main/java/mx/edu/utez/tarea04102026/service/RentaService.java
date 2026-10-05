package mx.edu.utez.tarea04102026.service;

import mx.edu.utez.tarea04102026.controller.dto.CotizacionResponseDto;
import mx.edu.utez.tarea04102026.controller.dto.RentaRequestDTO;
import mx.edu.utez.tarea04102026.exception.customExceptions.CustomBadRequestExceptions;
import org.springframework.stereotype.Service;

@Service
public class RentaService {

    public CotizacionResponseDto calcularRenta(RentaRequestDTO data) {
        if (data.getEdadConductor() < 18) {
            throw new CustomBadRequestExceptions("El conductor debe ser mayor de 18 años");
        }
        if (data.getDiasRenta() > 30) {
            throw new CustomBadRequestExceptions("La renta no puede superar los 30 días");
        }
        if (data.getKilometrosEstimados() > 5000) {
            throw new CustomBadRequestExceptions("Los kilómetros estimados no pueden superar los 5,000 km");
        }
        if ("CAMIONETA".equalsIgnoreCase(data.getTipoVehiculo()) && data.getEdadConductor() < 25) {
            throw new CustomBadRequestExceptions("Para rentar una CAMIONETA el conductor debe tener al menos 25 años");
        }

        double costoDiario = 0.0;
        switch (data.getTipoVehiculo().toUpperCase()) {
            case "COMPACTO":
                costoDiario = 550.0;
                break;
            case "SEDAN":
                costoDiario = 700.0;
                break;
            case "SUV":
                costoDiario = 950.0;
                break;
            case "CAMIONETA":
                costoDiario = 1200.0;
                break;
            default:
                throw new CustomBadRequestExceptions("Tipo de vehículo no válido");
        }

        double costoRentaBase = costoDiario * data.getDiasRenta();

        if (data.getDiasRenta() >= 7) {
            costoRentaBase = costoRentaBase * 0.90;
        }

        int kmIncluidos = data.getDiasRenta() * 100;
        double cargoKm = 0.0;
        if (data.getKilometrosEstimados() > kmIncluidos) {
            cargoKm = (data.getKilometrosEstimados() - kmIncluidos) * 4.0;
        }

        double cargoEdad = 0.0;
        if (data.getEdadConductor() >= 18 && data.getEdadConductor() <= 24) {
            cargoEdad = ((costoDiario * data.getDiasRenta()) + cargoKm) * 0.15;
        }

        double cargoSeguro = 0.0;
        if (data.isSeguroCompleto()) {
            cargoSeguro = data.getDiasRenta() * 180.0;
        }

        double total = costoRentaBase + cargoKm + cargoEdad + cargoSeguro;

        CotizacionResponseDto respuesta = new CotizacionResponseDto();
        respuesta.setDetalle("Renta para " + data.getNombreCliente());
        respuesta.setTotal(total);

        return respuesta;
    }
}