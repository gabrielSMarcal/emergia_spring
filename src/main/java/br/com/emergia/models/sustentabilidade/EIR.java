package br.com.emergia.models.sustentabilidade;


import br.com.emergia.models.subtotais.SubtotalAmbiental;
import br.com.emergia.database.Relatorio;
import br.com.emergia.models.subtotais.SubtotalContribuicaoHumana;
import br.com.emergia.repository.RelatorioRepository;

public class EIR {
    private RelatorioRepository relatorioRepository;


    public double calEIR(){
        Relatorio ultimo = relatorioRepository.findLatest()
                .orElseThrow(() -> new RuntimeException("Nenhum relatório encontrado"));

        SubtotalAmbiental subtotalAmbiental = new SubtotalAmbiental();

        SubtotalContribuicaoHumana subtotalContribuicaoHumana = new SubtotalContribuicaoHumana();

        // EIR = F / I
        // Mede quanto da energia total usada vem da sociedade urbana em relação à energia fornecida pela natureza.
        double resulEIR = subtotalContribuicaoHumana.calcContribuicaoHumana(
                ultimo.getRefBens(), ultimo.getRefCombustivelUsado(), ultimo.getRefEletricidade(), ultimo.getRefGado(),
                ultimo.getRefMaoObra(), ultimo.getRefMaquinarios(), ultimo.getRefRacao(), ultimo.getRefCuidadoSolo()) / subtotalAmbiental.somaAmbiental();

        return resulEIR;

        /*
        EIR < 1	Boa sustentabilidade. A natureza fornece mais energia do que a sociedade precisa investir.

        EIR ≈ 1	Equilíbrio entre natureza e sociedade.

        EIR > 1	Alta dependência da economia – sistema depende mais de bens e serviços humanos do que da natureza.

        */
    }

}
