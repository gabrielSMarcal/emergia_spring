package br.com.emergia.models;

import br.com.emergia.database.AtributosFixos;

public class ProducaoLeite extends AtributosFixos {

    // ha = hectares
    private double leitePorDia;
    private String uLeitePorDia = "L";
    private double haFazendaLeite;
    private String uHaFAzebdaLeite = "Ha";
    private double resulProducaoLeite;
    private double resulProducaoLeiteAno;

    private String uResulProducaoLeite = "unid/ano";
    private double resulRefEmergiaSolarProducaoLeite;
    private String uResulRefEmergiaSolarProducaoLeite = "seJ/unid";

    public ProducaoLeite(double leitePorDia, double haFazendaLeite) {
            this.haFazendaLeite = haFazendaLeite;
            this.leitePorDia = leitePorDia;
    }

    public double getLeitePorDia() {
        return leitePorDia;
    }

    public double getHaFazenda() {
        return haFazendaLeite;
    }

    public double calcPL () {

        resulProducaoLeiteAno = leitePorDia * getDiasAno() * getLitroParaGrama()
                * getKcalPorGramaLeite() * getJoulesPorKcalLeite();

        resulProducaoLeite = resulProducaoLeiteAno/haFazendaLeite;

        return resulProducaoLeiteAno;
    }

    public double calRefEmergiaSolarProducaoLeite (){
        resulRefEmergiaSolarProducaoLeite = calcPL() * getTransformidadeProducaoleite();

        return resulRefEmergiaSolarProducaoLeite;
    }
}
