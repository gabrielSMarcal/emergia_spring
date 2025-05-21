package br.com.emergia.controllers.calculadoras;

import br.com.emergia.models.contribuicaoHumana.operacoesProducao.Racao;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class RacaoController {

    @GetMapping("/racao/calc")
    public Map<String, Double> calcularRacao(
           @RequestParam int saca,
           @RequestParam float valorSaca) {
               
        Racao racao = new Racao(saca, valorSaca);
        double calc   = racao.calcR();
        double ref    = racao.calRefEmergiaSolarRacao();
        double razao  = racao.calcRazaoRacao();
        
        Map<String, Double> result = new HashMap<>();
        result.put("calc", calc);
        result.put("ref", ref);
        result.put("razao", razao);
        return result;
    }
}