package br.com.example.emergia.controllers;

import br.com.example.emergia.models.Gado;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GadoController {

    @GetMapping("/gado/calc")
    public double calcularGado(@RequestParam double pesoKgMedia, @RequestParam int numeroAnimais,
                               @RequestParam float anosVidaMedia) {
        Gado gado = new Gado(pesoKgMedia, numeroAnimais, anosVidaMedia);
        double emerGado = gado.calcGado();

        return emerGado;
    }
}
