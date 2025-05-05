package br.com.emergia.controllers;

import br.com.emergia.models.PerdaSolo;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class PerdaSoloController {

    @GetMapping("/perdaSolo/calc")
    public Map<String, Double> calcularPerdaSolo(@RequestParam double perdaDeSoloTonelada) {
        PerdaSolo perda = new PerdaSolo(perdaDeSoloTonelada);
        double calc   = perda.calcPS();
        double ref    = perda.calRefEmergiaSolarPerdaSolo();
        double razao  = perda.calcRazaoPerdaSolo();
        
        Map<String, Double> result = new HashMap<>();
        result.put("calc", calc);
        result.put("ref", ref);
        result.put("razao", razao);
        return result;
    }
}