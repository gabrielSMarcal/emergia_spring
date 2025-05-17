package br.com.emergia.models.subtotais;

public class SubtotalPoducao {

    private double totalProducaoLeite;

    public double getTotalProducaoLeite() {
        return totalProducaoLeite;
    }

    public double calcProducaoLeite (double refProducaoLeite) {

        // pega o valor ref de ProducaoLeite
        totalProducaoLeite = refProducaoLeite;

        return totalProducaoLeite;
    }
}
