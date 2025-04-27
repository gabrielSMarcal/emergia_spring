package br.com.emergia.controllers;

import br.com.emergia.models.Eletricidade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EletricidadeController {

    @GetMapping("/eletrica/calc")
    public double calcularEletricidade(@RequestParam double energiaPORKWH) {
        Eletricidade eletricidade = new Eletricidade(energiaPORKWH);
        double emerEletricidade = eletricidade.calcE();

        return emerEletricidade;
    }
}
