package br.com.emergia.models.subtotais;

import br.com.emergia.models.producao.ProducaoLeite;

public class SubtotalPoducao {

    private double totalProducaoLeite;

    public double getTotalProducaoLeite() {
        return totalProducaoLeite;
    }

    public double calcProducaoLeite () {

        // pega o valor ref de ProducaoLeite;
        ProducaoLeite prodL = new ProducaoLeite();
        totalProducaoLeite = prodL.getResulRefEmergiaSolarProducaoLeite();

        return totalProducaoLeite;
    }
}
