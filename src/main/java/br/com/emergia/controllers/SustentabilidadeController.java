package br.com.emergia.controllers;

import br.com.emergia.database.Sustentabilidade;
import br.com.emergia.services.SustentabilidadeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/sustentabilidade")
public class SustentabilidadeController {

    private final SustentabilidadeService service;
    public SustentabilidadeController(SustentabilidadeService service) {
        this.service = service;
    }

    @PostMapping("/calcular")
    public ResponseEntity<Sustentabilidade> calcular() {
        return ResponseEntity.ok(service.calcularESalvar());
    }

    @GetMapping("/ultima")
    public ResponseEntity<Sustentabilidade> ultima() {
        return ResponseEntity.ok(service.buscarUltima());
    }
}
