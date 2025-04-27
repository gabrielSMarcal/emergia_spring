package br.com.emergia.controllers;

import br.com.emergia.models.PerdaSolo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PerdaSoloController {

    @GetMapping("/perdaSolo/calc")
    public double calcularPerdaSolo(@RequestParam double perdaDeSoloTonelada) {
        PerdaSolo perda = new PerdaSolo(perdaDeSoloTonelada);

        double emerPerdaSolo = perda.calcPS();

        return emerPerdaSolo;
    }
}
