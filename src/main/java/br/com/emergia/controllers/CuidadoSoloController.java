package br.com.emergia.controllers;

import br.com.emergia.models.CuidadoSolo;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class CuidadoSoloController {

    @GetMapping("/solo/calc")
    public Map<String, Double> calcularCuidadoSolo(
          @RequestParam double toneladaPorHA,
          @RequestParam int ano,
          @RequestParam double areaDevastadaPeloGado) {
              
        CuidadoSolo solo = new CuidadoSolo(toneladaPorHA, areaDevastadaPeloGado, ano);
        double calc   = solo.calcCS();
        double ref    = solo.calRefEmergiaSolarCuidadoSolo();
        double razao  = solo.calcRazaoCuidadoSolo();
        
        Map<String, Double> result = new HashMap<>();
        result.put("calc", calc);
        result.put("ref", ref);
        result.put("razao", razao);
        return result;
    }
}