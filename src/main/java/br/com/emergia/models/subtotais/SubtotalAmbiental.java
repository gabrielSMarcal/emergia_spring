package br.com.emergia.models.subtotais;

import br.com.emergia.models.contribuicaoAmbiental.energiaNaoRenovavel.AguaUsada;
import br.com.emergia.models.contribuicaoAmbiental.energiaNaoRenovavel.PerdaSolo;
import br.com.emergia.models.contribuicaoAmbiental.energiaRenovavel.PotencialQuimico;

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

    public double calcRenovavel () {

        // pega o valor ref de PotencialQuimico
        PotencialQuimico pq = new PotencialQuimico();
        totalRenovavel = pq.getResulRefEmergiaSolarPotencialQuimico();

        return totalRenovavel;
    }

    public double calcNaoRenovavel () {

        // pega o valor ref de AguaUsada e PerdaSolo
        AguaUsada au = new AguaUsada();
        PerdaSolo ps = new PerdaSolo();

        totalNaoRenovavel = au.getResulRefEmergiaSolarAguaUsada() + ps.getResulRefEmergiaSolarPerdaSolo();

        return totalNaoRenovavel;
    }

    public double somaAmbiental () {

        totalAmbiental = calcRenovavel() + calcNaoRenovavel();

        return totalAmbiental;
    }
}
