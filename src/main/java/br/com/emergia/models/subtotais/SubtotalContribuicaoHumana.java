package br.com.emergia.models.subtotais;

public class SubtotalContribuicaoHumana {

    private double totalBens;
    private double totalOperacoesProducao;
    private double totalContribuicaoHumana;

    public double getTotalBens() {
        return totalBens;
    }

    public double getTotalOperacoesProducao() {
        return totalOperacoesProducao;
    }

    public double getTotalContribuicaoHumana() {
        return totalContribuicaoHumana;
    }

    // Recebe o valor "ref" do ValorConsumoManutencao (Bens)
    public double calcBens(double refVCM) {
        totalBens = refVCM;
        return totalBens;
    }

    // Recebe os valores "ref" das demais referências de operações de produção
    public double calcOperacoesProducao(double refCombustivel, double refEletricidade, double refGado,
                                          double refMaoObra, double refMaquinario, double refRacao, double refCuidadoSolo) {
        totalOperacoesProducao = refCombustivel + refEletricidade + refGado +
                                 refMaoObra + refMaquinario + refRacao + refCuidadoSolo;
        return totalOperacoesProducao;
    }

    // Soma os dois subtotais
    public double calcContribuicaoHumana(double refVCM, double refCombustivel, double refEletricidade, double refGado,
                                           double refMaoObra, double refMaquinario, double refRacao, double refCuidadoSolo) {
        totalContribuicaoHumana = calcBens(refVCM) + calcOperacoesProducao(refCombustivel, refEletricidade,
                                             refGado, refMaoObra, refMaquinario, refRacao, refCuidadoSolo);
        return totalContribuicaoHumana;
    }
}
