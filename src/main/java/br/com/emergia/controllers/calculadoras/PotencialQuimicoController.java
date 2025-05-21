package br.com.emergia.controllers.calculadoras;

import br.com.emergia.models.contribuicaoAmbiental.energiaRenovavel.PotencialQuimico;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class PotencialQuimicoController {

    @GetMapping("/potencialQuimico/calc")
    public Map<String, Double> calcularPotencialQuimico(
          @RequestParam double haFazenda,
          @RequestParam double mediaChuvaAnoMetros) {
              
        PotencialQuimico quimico = new PotencialQuimico(haFazenda, mediaChuvaAnoMetros);
        double calc   = quimico.calcPQ();
        double ref    = quimico.calRefEmergiaSolarPotencialQuimico();
        double razao  = quimico.calcRazaoPotencialQuimico();
        
        Map<String, Double> result = new HashMap<>();
        result.put("calc", calc);
        result.put("ref", ref);
        result.put("razao", razao);
        return result;
    }
}