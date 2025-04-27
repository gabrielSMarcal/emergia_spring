package br.com.example.emergia.controllers;

import br.com.example.emergia.models.Racao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RacaoController{

    @GetMapping("/racao/calc")
    public double calcularRacao(@RequestParam int saca, @RequestParam float valorSaca) {

        Racao racao = new Racao(saca, valorSaca);
        double emerRacao = racao.calcR();

        return emerRacao;
    }
}
