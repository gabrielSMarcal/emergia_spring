package br.com.emergia.services;

import br.com.emergia.models.subTotal.SubTotal;

import java.util.HashMap;
import java.util.Map;

public class SubtotalService {

    public Map<String, SubTotal> calculateSubtotals(Map<String, Map<String, Double>> results) {
        Map<String, SubTotal> subtotals = new HashMap<>();

        for (Map.Entry<String, Map<String, Double>> entry : results.entrySet()) {
            String key = entry.getKey();
            Map<String, Double> values = entry.getValue();

            String category = getCategoryFromKey(key);
            subtotals.putIfAbsent(category, new SubTotal(category));

            SubTotal subtotal = subtotals.get(category);
            subtotal.addValues(
                    values.getOrDefault("calc", 0.0),
                    values.getOrDefault("ref", 0.0),
                    values.getOrDefault("razao", 0.0)
            );
        }

        return subtotals;
    }

    private String getCategoryFromKey(String key) {
        if (key.contains("contribuicaoAmbiental")) {
            return "Contribuição Ambiental";
        } else if (key.contains("contribuicaoHumana")) {
            return "Contribuição Humana";
        } else if (key.contains("producao")) {
            return "Produção";
        }
        return "Outros";
    }
}