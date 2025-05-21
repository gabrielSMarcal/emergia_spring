package br.com.emergia.controllers;

import br.com.emergia.models.contribuicaoHumana.operacoesProducao.CombustivelUsado;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class CombustivelUsadoController {

    @GetMapping("/combustivel/calc")
    public Map<String, Double> calcularCombustivelUsado(
          @RequestParam double horasTratorPorAno,
          @RequestParam double qtdTrator,
          @RequestParam double litrosPorHora) {
              
        CombustivelUsado combustivel = new CombustivelUsado(horasTratorPorAno, qtdTrator, litrosPorHora);
        double calc   = combustivel.calCombustivelUsado();
        double ref    = combustivel.calRefEmergiaSolarCombustivelUsado();
        double razao  = combustivel.calcRazaoCombustivelUsado();
        
        Map<String, Double> result = new HashMap<>();
        result.put("calc", calc);
        result.put("ref", ref);
        result.put("razao", razao);
        return result;
    }
}
