package br.com.emergia.controllers.calculadoras;

import br.com.emergia.models.contribuicaoHumana.operacoesProducao.Eletricidade;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class EletricidadeController {

    @GetMapping("/eletrica/calc")
    public Map<String, Double> calcularEletricidade(@RequestParam double energiaPORKWH) {
        Eletricidade eletricidade = new Eletricidade(energiaPORKWH);
        double calc   = eletricidade.calcE();
        double ref    = eletricidade.calRefEmergiaSolarEletricidade();
        double razao  = eletricidade.calcRazaoEletricidade();
        
        Map<String, Double> result = new HashMap<>();
        result.put("calc", calc);
        result.put("ref", ref);
        result.put("razao", razao);
        return result;
    }
}