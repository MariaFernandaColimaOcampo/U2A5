package mx.edu.utez.tarea04102026.controller;

import mx.edu.utez.tarea04102026.dto.ApuestaRequest;
import mx.edu.utez.tarea04102026.model.Partida;
import mx.edu.utez.tarea04102026.model.Tiro;
import mx.edu.utez.tarea04102026.service.PartidaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidas")
public class PartidaController {

    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping("/iniciar")
    public ResponseEntity<Partida> iniciar(@Valid @RequestBody ApuestaRequest req) {
        Partida nuevaPartida = partidaService.iniciarPartida(req.getIdJugador(), req.getMonto());
        return new ResponseEntity<>(nuevaPartida, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/tiro")
    public ResponseEntity<Tiro> tirar(@PathVariable Long id) {
        return ResponseEntity.ok(partidaService.realizarTiro(id));
    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<Partida> finalizar(@PathVariable Long id) {
        return ResponseEntity.ok(partidaService.finalizarPartidaManual(id));
    }

    @GetMapping("/jugador/{idJugador}")
    public ResponseEntity<List<Partida>> historial(@PathVariable Long idJugador) {
        return ResponseEntity.ok(partidaService.obtenerHistorialPartidas(idJugador));
    }
}