package br.com.emergia.controllers;

import br.com.emergia.models.Maquinarios;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class MaquinariosController {

    @GetMapping("/maquinario/calc")
    public Map<String, Double> calcularMaquinario(
           @RequestParam double qtdHoraTrator,
           @RequestParam double valorHoraTrator) {
               
        Maquinarios maquinario = new Maquinarios(qtdHoraTrator, valorHoraTrator);
        double calc   = maquinario.calcM();
        double ref    = maquinario.calRefEmergiaSolarMaquinario();
        double razao  = maquinario.calcRazaoMaquinario();
        
        Map<String, Double> result = new HashMap<>();
        result.put("calc", calc);
        result.put("ref", ref);
        result.put("razao", razao);
        return result;
    }
}