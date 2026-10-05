package mx.edu.utez.tarea04102026.controller;

import jakarta.validation.Valid;
import mx.edu.utez.tarea04102026.controller.dto.CotizacionResponseDto;
import mx.edu.utez.tarea04102026.controller.dto.HospedajeRequestDTO;
import mx.edu.utez.tarea04102026.service.HospedajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/hospedaje")
public class HospedajeController {

    private final HospedajeService hospedajeService;

    public HospedajeController(HospedajeService hospedajeService) {
        this.hospedajeService = hospedajeService;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<CotizacionResponseDto> cotizar(
            @org.springframework.web.bind.annotation.RequestBody @Valid HospedajeRequestDTO payload) {
        return ResponseEntity
                .status(200)
                .body(hospedajeService.calcularHospedaje(payload));
    }
}