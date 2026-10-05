package mx.edu.utez.tarea04102026.controller;

import jakarta.validation.Valid;
import mx.edu.utez.tarea04102026.controller.dto.CotizacionResponseDto;
import mx.edu.utez.tarea04102026.controller.dto.RentaRequestDTO;
import mx.edu.utez.tarea04102026.service.RentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/rentas")
public class RentaController {

    private final RentaService rentaService;

    public RentaController(RentaService rentaService) {
        this.rentaService = rentaService;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<CotizacionResponseDto> cotizar(
            @org.springframework.web.bind.annotation.RequestBody @Valid RentaRequestDTO payload) {
        return ResponseEntity
                .status(200)
                .body(rentaService.calcularRenta(payload));
    }
}