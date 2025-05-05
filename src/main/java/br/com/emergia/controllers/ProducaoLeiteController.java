package br.com.emergia.controllers;

import br.com.emergia.models.ProducaoLeite;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ProducaoLeiteController {

    @GetMapping("/producaoLeite/calc")
    public Map<String, Double> calcularLeite(
          @RequestParam double leitePorDia,
          @RequestParam double haFazendaLeite) {
              
        ProducaoLeite leite = new ProducaoLeite(leitePorDia, haFazendaLeite);
        double calc   = leite.calcPL();
        double ref    = leite.calRefEmergiaSolarProducaoLeite();
        double razao  = leite.calcRazaoProducaoLeite();
        
        Map<String, Double> result = new HashMap<>();
        result.put("calc", calc);
        result.put("ref", ref);
        result.put("razao", razao);
        return result;
    }
}