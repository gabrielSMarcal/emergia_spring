package br.com.emergia.controllers;

import br.com.emergia.models.ProducaoLeite;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducaoLeiteController {

    @GetMapping("/producaoLeite/calc")
    public double calcularLeite(@RequestParam double leitePorDia, @RequestParam double haFazendaLeite) {

        ProducaoLeite leite = new ProducaoLeite(leitePorDia, haFazendaLeite);
        double emerLeite = leite.calcPL();

        return emerLeite;
    }
}