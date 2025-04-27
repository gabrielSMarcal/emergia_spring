package br.com.example.emergia.controllers;

import br.com.example.emergia.models.MaoObra;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MaoObraController {

    @GetMapping("/maoObra/calc")
    public double calcularMaoObra(@RequestParam int pessoa, @RequestParam double horasTrabalhadas,
                                  @RequestParam int qtdDiasTrabalhado) {
        MaoObra maoObra = new MaoObra(pessoa, horasTrabalhadas, qtdDiasTrabalhado);

        double emerMaoObra = maoObra.calcMO();

        return emerMaoObra;
    }
}
