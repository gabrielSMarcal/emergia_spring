package br.com.example.emergia.controllers;

import br.com.example.emergia.models.AguaUsada;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AguaUsadaController {

    @GetMapping("/aguaUsada/calc")
    public double calcularAguaUsada(@RequestParam double litroPorDiaAnimal,@RequestParam int qtdAnimal,
                                    @RequestParam double litrosAdicionalDia) {
        AguaUsada agua = new AguaUsada(litroPorDiaAnimal, qtdAnimal, litrosAdicionalDia);
        double emerAgua = agua.calcAU();

        return emerAgua;
    }
}
