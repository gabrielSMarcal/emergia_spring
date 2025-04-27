package br.com.example.emergia.controllers;

import br.com.example.emergia.models.CuidadoSolo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CuidadoSoloController {

    @GetMapping("/solo/calc")
    public double calcularCuidadoSolo(@RequestParam double toneladaPorHA, @RequestParam int ano,
                                      @RequestParam double areaDevastadaPeloGado) {
        CuidadoSolo solo = new CuidadoSolo(toneladaPorHA, areaDevastadaPeloGado, ano);
        double emerSolo = solo.calcCS();

        return emerSolo;
    }
}
