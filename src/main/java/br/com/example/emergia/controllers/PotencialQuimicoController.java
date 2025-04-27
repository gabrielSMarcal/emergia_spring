package br.com.example.emergia.controllers;

import br.com.example.emergia.models.PotencialQuimico;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PotencialQuimicoController {

    @GetMapping("/potencialQuimico/calc")
    public double calcularPotencialQuimico(@RequestParam double haFazenda, @RequestParam double mediaChuvaAnoMetros) {
        PotencialQuimico quimico = new PotencialQuimico(haFazenda, mediaChuvaAnoMetros);

        double emerPotencialQuimico = quimico.calcPQ();

        return emerPotencialQuimico;
    }
}
