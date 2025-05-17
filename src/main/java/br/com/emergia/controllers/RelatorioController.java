package br.com.emergia.controllers;

import br.com.emergia.database.Relatorio;
import br.com.emergia.models.subtotais.CalculoTotal;
import br.com.emergia.repository.RelatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RelatorioController {

    @Autowired
    private RelatorioRepository relatorioRepository;
    
    @Autowired
    private CalculoTotal calculoTotal; // Injeção pelo Spring

    // Método auxiliar movido para fora do método salvarResultados
    private Double getDoubleValue(Map<String, Object> map, String key) {
        Object value = map.get(key);
        return (value != null) ? Double.valueOf(value.toString()) : null;
    }

    @PostMapping("/storeResults")
    public Relatorio salvarResultados(@RequestBody Map<String, Object> calcResults) {
        Relatorio relatorio = new Relatorio();

        // Trata o nome da fazenda
        if (calcResults.containsKey("nomeFazenda")) {
            relatorio.setNomeDaFazenda(calcResults.get("nomeFazenda").toString());
        }

        // Cuidado Solo
        if (calcResults.containsKey("cuidadoSolo")) {
            Map<String, Object> solo = (Map<String, Object>) calcResults.get("cuidadoSolo");
            relatorio.setCalcCuidadoSolo(getDoubleValue(solo, "calc"));
            relatorio.setRefCuidadoSolo(getDoubleValue(solo, "ref"));
            relatorio.setRazaoCuidadoSolo(getDoubleValue(solo, "razao"));
        }

        // Potencial Químico
        if (calcResults.containsKey("potencialQuimico")) {
            Map<String, Object> pq = (Map<String, Object>) calcResults.get("potencialQuimico");
            relatorio.setCalcPotencialQuimico(getDoubleValue(pq, "calc"));
            relatorio.setRefPotencialQuimico(getDoubleValue(pq, "ref"));
            relatorio.setRazaoPotencialQuimico(getDoubleValue(pq, "razao"));
        }

        // Produção de Leite
        if (calcResults.containsKey("producaoLeite")) {
            Map<String, Object> pl = (Map<String, Object>) calcResults.get("producaoLeite");
            relatorio.setCalcProducaoLeite(getDoubleValue(pl, "calc"));
            relatorio.setRefProducaoLeite(getDoubleValue(pl, "ref"));
            relatorio.setRazaoProducaoLeite(getDoubleValue(pl, "razao"));
        }

        // Ração
        if (calcResults.containsKey("racao")) {
            Map<String, Object> racao = (Map<String, Object>) calcResults.get("racao");
            relatorio.setCalcRacao(getDoubleValue(racao, "calc"));
            relatorio.setRefRacao(getDoubleValue(racao, "ref"));
            relatorio.setRazaoRacao(getDoubleValue(racao, "razao"));
        }

        // Perda Solo
        if (calcResults.containsKey("perdaSolo")) {
            Map<String, Object> ps = (Map<String, Object>) calcResults.get("perdaSolo");
            relatorio.setCalcPerdaSolo(getDoubleValue(ps, "calc"));
            relatorio.setRefPerdaSolo(getDoubleValue(ps, "ref"));
            relatorio.setRazaoPerdaSolo(getDoubleValue(ps, "razao"));
        }

        // Água Usada
        if (calcResults.containsKey("aguaUsada")) {
            Map<String, Object> agua = (Map<String, Object>) calcResults.get("aguaUsada");
            relatorio.setCalcAguaUsada(getDoubleValue(agua, "calc"));
            relatorio.setRefAguaUsada(getDoubleValue(agua, "ref"));
            relatorio.setRazaoAguaUsada(getDoubleValue(agua, "razao"));
        }

        // Combustível Usado
        if (calcResults.containsKey("combustivelUsado")) {
            Map<String, Object> comb = (Map<String, Object>) calcResults.get("combustivelUsado");
            relatorio.setCalcCombustivelUsado(getDoubleValue(comb, "calc"));
            relatorio.setRefCombustivelUsado(getDoubleValue(comb, "ref"));
            relatorio.setRazaoCombustivelUsado(getDoubleValue(comb, "razao"));
        }

        // Eletricidade
        if (calcResults.containsKey("eletricidade")) {
            Map<String, Object> eletri = (Map<String, Object>) calcResults.get("eletricidade");
            relatorio.setCalcEletricidade(getDoubleValue(eletri, "calc"));
            relatorio.setRefEletricidade(getDoubleValue(eletri, "ref"));
            relatorio.setRazaoEletricidade(getDoubleValue(eletri, "razao"));
        }

        // Gado
        if (calcResults.containsKey("gado")) {
            Map<String, Object> gado = (Map<String, Object>) calcResults.get("gado");
            relatorio.setCalcGado(getDoubleValue(gado, "calc"));
            relatorio.setRefGado(getDoubleValue(gado, "ref"));
            relatorio.setRazaoGado(getDoubleValue(gado, "razao"));
        }

        // Maquinários
        if (calcResults.containsKey("maquinarios")) {
            Map<String, Object> maq = (Map<String, Object>) calcResults.get("maquinarios");
            relatorio.setCalcMaquinarios(getDoubleValue(maq, "calc"));
            relatorio.setRefMaquinarios(getDoubleValue(maq, "ref"));
            relatorio.setRazaoMaquinarios(getDoubleValue(maq, "razao"));
        }

        // Mão de Obra
        if (calcResults.containsKey("maoObra")) {
            Map<String, Object> mo = (Map<String, Object>) calcResults.get("maoObra");
            relatorio.setCalcMaoObra(getDoubleValue(mo, "calc"));
            relatorio.setRefMaoObra(getDoubleValue(mo, "ref"));
            relatorio.setRazaoMaoObra(getDoubleValue(mo, "razao"));
        }

        // Valor Consumo/Manutenção (Bens)
        if (calcResults.containsKey("bens")) {
            Map<String, Object> bens = (Map<String, Object>) calcResults.get("bens");
            relatorio.setCalcBens(getDoubleValue(bens, "calc"));
            relatorio.setRefBens(getDoubleValue(bens, "ref"));
            relatorio.setRazaoBens(getDoubleValue(bens, "razao"));
        }

        return relatorioRepository.save(relatorio);
    }

    @GetMapping("/getLastResults")
    public Relatorio getLastResults() {
        return relatorioRepository.findLatest()
                 .orElseThrow(() -> new RuntimeException("Nenhum relatório encontrado"));
    }
    
    @GetMapping("/getCalculoTotal")
    public CalculoTotal getCalculoTotal() {
        calculoTotal.calcularTotais();
        return calculoTotal;
    }
}
