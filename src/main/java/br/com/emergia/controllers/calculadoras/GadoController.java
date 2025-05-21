package br.com.emergia.controllers.calculadoras;

import br.com.emergia.models.contribuicaoHumana.operacoesProducao.Gado;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class GadoController {

    @GetMapping("/gado/calc")
    public Map<String, Double> calcularGado(
           @RequestParam double pesoKgMedia,
           @RequestParam int numeroAnimais,
           @RequestParam float anosVidaMedia) {
               
        Gado gado = new Gado(pesoKgMedia, numeroAnimais, anosVidaMedia);
        double calc   = gado.calcG();
        double ref    = gado.calRefEmergiaSolarGado();
        double razao  = gado.calcRazaoGado();
        
        Map<String, Double> result = new HashMap<>();
        result.put("calc", calc);
        result.put("ref", ref);
        result.put("razao", razao);
        return result;
    }
}