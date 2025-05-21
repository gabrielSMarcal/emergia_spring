package br.com.emergia.controllers.calculadoras;

import br.com.emergia.models.contribuicaoAmbiental.energiaNaoRenovavel.AguaUsada;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AguaUsadaController {

    @GetMapping("/aguaUsada/calc")
    public Map<String, Double> calcularAguaUsada(
          @RequestParam double litroPorDiaAnimal,
          @RequestParam int qtdAnimal,
          @RequestParam double litrosAdicionalDia) {
              
        AguaUsada agua = new AguaUsada(litroPorDiaAnimal, qtdAnimal, litrosAdicionalDia);
        double calc   = agua.calcAU();
        double ref    = agua.calRefEmergiaSolarAguaUsada();
        double razao  = agua.calcRazaoAguaUsada();
        
        Map<String, Double> result = new HashMap<>();
        result.put("calc", calc);
        result.put("ref", ref);
        result.put("razao", razao);
        return result;
    }
}
