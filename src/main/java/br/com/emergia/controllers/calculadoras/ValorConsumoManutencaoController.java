package br.com.emergia.controllers.calculadoras;

import br.com.emergia.models.contribuicaoHumana.bens.ValorConsumoManutencao;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ValorConsumoManutencaoController {

    @GetMapping("/consumoFazenda/calc")
    public Map<String, Double> calcularManutencao(
          @RequestParam double bens,
          @RequestParam int anos) {
              
        ValorConsumoManutencao consumo = new ValorConsumoManutencao(bens, anos);
        double calc   = consumo.calcBens();
        double ref    = consumo.calRefEmergiaSolarVCM();
        double razao  = consumo.calcRazaoVCM();
        
        Map<String, Double> result = new HashMap<>();
        result.put("calc", calc);
        result.put("ref", ref);
        result.put("razao", razao);
        return result;
    }
}