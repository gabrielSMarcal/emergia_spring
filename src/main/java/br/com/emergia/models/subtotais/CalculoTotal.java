package br.com.emergia.models.subtotais;

public class CalculoTotal {

    private double totalRenovavel;
    private double totalNaoRenovavel;
    private double totalAmbiental;

    private double totalBens;
    private double totalOperacoesProducao;
    private double totalContribuicaoHumana;

    private double totalProducao;

    private double calculoTotal;

    public double getCalculoTotal() {
        return calculoTotal;
    }

    public void calcularTotais() {
        // Calcula os subtotais ambientais
        SubtotalAmbiental subtotalAmbiental = new SubtotalAmbiental();
        totalRenovavel = subtotalAmbiental.calcRenovavel();
        totalNaoRenovavel = subtotalAmbiental.calcNaoRenovavel();
        totalAmbiental = subtotalAmbiental.somaAmbiental();

        // Calcula os subtotais de contribuição humana
        SubtotalContribuicaoHumana subtotalContribuicaoHumana = new SubtotalContribuicaoHumana();
        totalBens = subtotalContribuicaoHumana.calcBens();
        totalOperacoesProducao = subtotalContribuicaoHumana.calcOperacoesProducao();
        totalContribuicaoHumana = subtotalContribuicaoHumana.calcContribuicaoHumana();

        // Calcula o subtotal de produção
        SubtotalPoducao subtotalPoducao = new SubtotalPoducao();
        totalProducao = subtotalPoducao.calcProducaoLeite();

        // Calcula o total geral
        calculoTotal = totalAmbiental + totalContribuicaoHumana + totalProducao;
    }

    public double getPorcentagemRenovavel() {
        return (totalRenovavel / calculoTotal) * 100;
    }

    public double getPorcentagemNaoRenovavel() {
        return (totalNaoRenovavel / calculoTotal) * 100;
    }

    public double getPorcentagemAmbiental() {
        return (totalAmbiental / calculoTotal) * 100;
    }

    public double getPorcentagemBens() {
        return (totalBens / calculoTotal) * 100;
    }

    public double getPorcentagemOperacoesProducao() {
        return (totalOperacoesProducao / calculoTotal) * 100;
    }

    public double getPorcentagemContribuicaoHumana() {
        return (totalContribuicaoHumana / calculoTotal) * 100;
    }

    public double getPorcentagemProducao() {
        return (totalProducao / calculoTotal) * 100;
    }

    public double getTotalRenovavel() {
        return totalRenovavel;
    }

    public double getTotalNaoRenovavel() {
        return totalNaoRenovavel;
    }

    public double getTotalAmbiental() {
        return totalAmbiental;
    }

    public double getTotalBens() {
        return totalBens;
    }

    public double getTotalOperacoesProducao() {
        return totalOperacoesProducao;
    }

    public double getTotalContribuicaoHumana() {
        return totalContribuicaoHumana;
    }

    public double getTotalProducao() {
        return totalProducao;
    }
}
