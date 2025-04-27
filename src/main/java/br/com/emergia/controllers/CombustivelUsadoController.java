package br.com.emergia.controllers;

import br.com.emergia.models.CombustivelUsado;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CombustivelUsadoController {

    @GetMapping("/combustivel/calc")
    public double calcularCombustivelUsado(@RequestParam double horasTratorPorAno,@RequestParam double qtdTrator,
                                           @RequestParam double litrosPorHoras) {
        CombustivelUsado combustivel = new CombustivelUsado(horasTratorPorAno, qtdTrator, litrosPorHoras);
        double emerCombustivel = combustivel.calcCombustivelUsado();

        return emerCombustivel;
    }
}
