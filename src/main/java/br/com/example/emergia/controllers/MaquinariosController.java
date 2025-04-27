package br.com.example.emergia.controllers;

import br.com.example.emergia.models.Maquinarios;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MaquinariosController {

    @GetMapping("/maquinario/calc")
    public double calcularMaquinario(@RequestParam double qtdHoraTrator, @RequestParam double valorHoraTrator) {
        Maquinarios maquinario = new Maquinarios(qtdHoraTrator, valorHoraTrator);

        double emerMaquinario = maquinario.calcM();

        return emerMaquinario;
    }
}
