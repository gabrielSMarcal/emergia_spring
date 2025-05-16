package br.com.emergia.models.producao;

import br.com.emergia.database.AtributosFixos;

public class ProducaoLeite extends AtributosFixos {

    // ha = hectares
    private double leitePorDia;
    private double haFazendaLeite;

    private double resulProducaoLeite;
    private double resulProducaoLeiteAno;
    private double resulRefEmergiaSolarProducaoLeite;
    private double razaoProducaoLeite;

    private String unidadeProducaoLeite = "J";

    public ProducaoLeite(double leitePorDia, double haFazendaLeite) {

            this.haFazendaLeite = haFazendaLeite;
            this.leitePorDia = leitePorDia;
    }

    public String getUnidadeProducaoLeite() {
        return unidadeProducaoLeite;
    }

    public double calcPL () {

        resulProducaoLeiteAno = leitePorDia * getDiasAno() * getLitroParaGrama()
                * getKcalPorGramaLeite() * getJoulesPorKcalLeite();

        resulProducaoLeite = resulProducaoLeiteAno/haFazendaLeite;

        return resulProducaoLeiteAno;
    }

    public double calRefEmergiaSolarProducaoLeite () {

        resulRefEmergiaSolarProducaoLeite = calcPL() * getTransformidadeProducaoleite();

        return resulRefEmergiaSolarProducaoLeite;
    }

    public double calcRazaoProducaoLeite() {

        double base = calcPL();
        if(base == 0) return 0;

        razaoProducaoLeite = calRefEmergiaSolarProducaoLeite() / base;

        return razaoProducaoLeite;
    }
}
