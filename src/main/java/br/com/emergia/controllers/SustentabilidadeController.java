package br.com.emergia.controllers;
import br.com.emergia.database.Sustentabilidade;
import br.com.emergia.services.SustentabilidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sustentabilidade")
public class SustentabilidadeController {
    
    private final SustentabilidadeService sustentabilidadeService;
    
    @Autowired
    public SustentabilidadeController(SustentabilidadeService sustentabilidadeService) {
        this.sustentabilidadeService = sustentabilidadeService;
    }
    
    @PostMapping("/calcular/{relatorioId}")
    public ResponseEntity<Sustentabilidade> calcularIndices(@PathVariable Long relatorioId) {
        Sustentabilidade sustentabilidade = sustentabilidadeService.calcularEArmazenarIndices(relatorioId);
        return ResponseEntity.ok(sustentabilidade);
    }
    
    @GetMapping("/latest")
    public ResponseEntity<Sustentabilidade> getLatestSustentabilidade() {
        return sustentabilidadeService.getLatestSustentabilidade()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Sustentabilidade>> getAllSustentabilidade() {
        List<Sustentabilidade> sustentabilidades = sustentabilidadeService.getAllSustentabilidade();
        return ResponseEntity.ok(sustentabilidades);
    }
}
