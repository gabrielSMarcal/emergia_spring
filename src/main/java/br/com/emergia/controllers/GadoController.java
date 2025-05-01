package br.com.emergia.controllers;

import br.com.emergia.models.Gado;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class GadoController {

    @GetMapping("/gado/calc")
    public double calcularGado(@RequestParam double pesoKgMedia, @RequestParam int numeroAnimais,
                               @RequestParam float anosVidaMedia) {
        Gado gado = new Gado(pesoKgMedia, numeroAnimais, anosVidaMedia);
        double emerGado = gado.calcGado();

        return emerGado;
    }
}
