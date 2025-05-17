package br.com.emergia.controllers;

import br.com.emergia.database.Relatorio;
import br.com.emergia.repository.RelatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RelatorioController {

    @Autowired
    private RelatorioRepository relatorioRepository;

    @PostMapping("/storeResults")
    public Relatorio salvarResultados(@RequestBody Map<String, Map<String, Double>> calcResults) {
        Relatorio relatorio = new Relatorio();

        // Cuidado Solo
        if (calcResults.containsKey("cuidadoSolo")) {
            Map<String, Double> solo = calcResults.get("cuidadoSolo");
            relatorio.setCalcCuidadoSolo(solo.getOrDefault("calc", null));
            relatorio.setRefCuidadoSolo(solo.getOrDefault("ref", null));
            relatorio.setRazaoCuidadoSolo(solo.getOrDefault("razao", null));
        }

        // Potencial Químico
        if (calcResults.containsKey("potencialQuimico")) {
            Map<String, Double> pq = calcResults.get("potencialQuimico");
            relatorio.setCalcPotencialQuimico(pq.getOrDefault("calc", null));
            relatorio.setRefPotencialQuimico(pq.getOrDefault("ref", null));
            relatorio.setRazaoPotencialQuimico(pq.getOrDefault("razao", null));
        }

        // Produção de Leite
        if (calcResults.containsKey("producaoLeite")) {
            Map<String, Double> pl = calcResults.get("producaoLeite");
            relatorio.setCalcProducaoLeite(pl.getOrDefault("calc", null));
            relatorio.setRefProducaoLeite(pl.getOrDefault("ref", null));
            relatorio.setRazaoProducaoLeite(pl.getOrDefault("razao", null));
        }

        // Ração
        if (calcResults.containsKey("racao")) {
            Map<String, Double> racao = calcResults.get("racao");
            relatorio.setCalcRacao(racao.getOrDefault("calc", null));
            relatorio.setRefRacao(racao.getOrDefault("ref", null));
            relatorio.setRazaoRacao(racao.getOrDefault("razao", null));
        }

        // Perda Solo
        if (calcResults.containsKey("perdaSolo")) {
            Map<String, Double> ps = calcResults.get("perdaSolo");
            relatorio.setCalcPerdaSolo(ps.getOrDefault("calc", null));
            relatorio.setRefPerdaSolo(ps.getOrDefault("ref", null));
            relatorio.setRazaoPerdaSolo(ps.getOrDefault("razao", null));
        }

        // Água Usada
        if (calcResults.containsKey("aguaUsada")) {
            Map<String, Double> agua = calcResults.get("aguaUsada");
            relatorio.setCalcAguaUsada(agua.getOrDefault("calc", null));
            relatorio.setRefAguaUsada(agua.getOrDefault("ref", null));
            relatorio.setRazaoAguaUsada(agua.getOrDefault("razao", null));
        }

        // Combustível Usado
        if (calcResults.containsKey("combustivelUsado")) {
            Map<String, Double> comb = calcResults.get("combustivelUsado");
            relatorio.setCalcCombustivelUsado(comb.getOrDefault("calc", null));
            relatorio.setRefCombustivelUsado(comb.getOrDefault("ref", null));
            relatorio.setRazaoCombustivelUsado(comb.getOrDefault("razao", null));
        }

        // Eletricidade
        if (calcResults.containsKey("eletricidade")) {
            Map<String, Double> eletri = calcResults.get("eletricidade");
            relatorio.setCalcEletricidade(eletri.getOrDefault("calc", null));
            relatorio.setRefEletricidade(eletri.getOrDefault("ref", null));
            relatorio.setRazaoEletricidade(eletri.getOrDefault("razao", null));
        }

        // Gado
        if (calcResults.containsKey("gado")) {
            Map<String, Double> gado = calcResults.get("gado");
            relatorio.setCalcGado(gado.getOrDefault("calc", null));
            relatorio.setRefGado(gado.getOrDefault("ref", null));
            relatorio.setRazaoGado(gado.getOrDefault("razao", null));
        }

        // Maquinários
        if (calcResults.containsKey("maquinarios")) {
            Map<String, Double> maq = calcResults.get("maquinarios");
            relatorio.setCalcMaquinarios(maq.getOrDefault("calc", null));
            relatorio.setRefMaquinarios(maq.getOrDefault("ref", null));
            relatorio.setRazaoMaquinarios(maq.getOrDefault("razao", null));
        }

        // Mão de Obra
        if (calcResults.containsKey("maoObra")) {
            Map<String, Double> mo = calcResults.get("maoObra");
            relatorio.setCalcMaoObra(mo.getOrDefault("calc", null));
            relatorio.setRefMaoObra(mo.getOrDefault("ref", null));
            relatorio.setRazaoMaoObra(mo.getOrDefault("razao", null));
        }

        // Valor Consumo/Manutenção (Bens)
        if (calcResults.containsKey("bens")) {
            Map<String, Double> bens = calcResults.get("bens");
            relatorio.setCalcBens(bens.getOrDefault("calc", null));
            relatorio.setRefBens(bens.getOrDefault("ref", null));
            relatorio.setRazaoBens(bens.getOrDefault("razao", null));
        }

        return relatorioRepository.save(relatorio);
    }

    @GetMapping("/getLastResults")
    public Relatorio getLastResults() {
        return relatorioRepository.findLatest()
                 .orElseThrow(() -> new RuntimeException("Nenhum relatório encontrado"));
    }
}
