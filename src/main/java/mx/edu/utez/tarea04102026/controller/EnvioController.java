package mx.edu.utez.tarea04102026.controller;

import jakarta.validation.Valid;
import mx.edu.utez.tarea04102026.controller.dto.CotizacionResponseDto;
import mx.edu.utez.tarea04102026.controller.dto.EnvioRequestDTO;
import mx.edu.utez.tarea04102026.service.EnvioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/envios")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<CotizacionResponseDto> cotizar(
            @org.springframework.web.bind.annotation.RequestBody @Valid EnvioRequestDTO payload) {
        return ResponseEntity
                .status(200)
                .body(envioService.calcularEnvio(payload));
    }
}