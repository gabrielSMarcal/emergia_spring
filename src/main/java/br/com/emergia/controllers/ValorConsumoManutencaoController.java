package br.com.emergia.controllers;

import br.com.emergia.models.ValorConsumoManutencao;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ValorConsumoManutencaoController {

    @GetMapping("/consumoFazenda/calc")
    public double calcularManutencao(@RequestParam double bens, @RequestParam int anos) {

        ValorConsumoManutencao consumo = new ValorConsumoManutencao(bens, anos);
        double emerValorConsumoManutencao = consumo.calcBens();

        return emerValorConsumoManutencao;
    }
}
