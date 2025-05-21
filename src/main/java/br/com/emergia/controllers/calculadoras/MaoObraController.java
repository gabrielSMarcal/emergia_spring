package br.com.emergia.controllers;

import br.com.emergia.models.contribuicaoHumana.operacoesProducao.MaoObra;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class MaoObraController {

    @GetMapping("/maoObra/calc")
    public Map<String, Double> calcularMaoObra(
           @RequestParam int pessoa,
           @RequestParam double horasTrabalhadas,
           @RequestParam int qtdDiasTrabalhado) {
               
        MaoObra maoObra = new MaoObra(pessoa, horasTrabalhadas, qtdDiasTrabalhado);
        double calc   = maoObra.calcMO();
        double ref    = maoObra.calRefEmergiaSolarMaoObra();
        double razao  = maoObra.calcRazaoMaoObra();
        
        Map<String, Double> result = new HashMap<>();
        result.put("calc", calc);
        result.put("ref", ref);
        result.put("razao", razao);
        return result;
    }
}