package br.com.emergia.models.subtotais;

public class SubtotalAmbiental {

    private double totalRenovavel;
    private double totalNaoRenovavel;
    private double totalAmbiental;

    public double getTotalRenovavel() {
        return totalRenovavel;
    }

    public double getTotalNaoRenovavel() {
        return totalNaoRenovavel;
    }

    public double getTotalAmbiental() {
        return totalAmbiental;
    }

    // Agora o método recebe como parâmetro o valor "ref" de PotencialQuimico vindo do banco de dados
    public double calcRenovavel(double refPotencialQuimico) {
        totalRenovavel = refPotencialQuimico;
        return totalRenovavel;
    }

    // Recebe os valores "ref" de AguaUsada e PerdaSolo
    public double calcNaoRenovavel(double refAguaUsada, double refPerdaSolo) {
        totalNaoRenovavel = refAguaUsada + refPerdaSolo;
        return totalNaoRenovavel;
    }

    public double somaAmbiental() {
        totalAmbiental = totalRenovavel + totalNaoRenovavel;
        return totalAmbiental;
    }
}
