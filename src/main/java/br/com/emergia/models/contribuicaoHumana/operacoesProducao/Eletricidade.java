package br.com.emergia.models.contribuicaoHumana.operacoesProducao;

import br.com.emergia.database.AtributosFixos;

public class Eletricidade extends AtributosFixos {

    private double energiaPORKWH;

    private double resulEletricidade;
    private double resulRefEmergiaSolarEletricidade;
    private double razaoEletricidade;

    public Eletricidade() {}

    public Eletricidade (double energiaPORKWH) {
        this.energiaPORKWH = energiaPORKWH;
    }

    public double getResulRefEmergiaSolarEletricidade() {
        return resulRefEmergiaSolarEletricidade;
    }

    public double calcE() {
        resulEletricidade = (energiaPORKWH * getMesAno() * getKwhParaKcal()) * getEnergiaPorKcal();
        return resulEletricidade;
    }

    public double calRefEmergiaSolarEletricidade() {

        resulRefEmergiaSolarEletricidade = calcE() * getTransformidadeEletricidade();
        return resulRefEmergiaSolarEletricidade;
    }

    public double calcRazaoEletricidade() {
        
        double base = calcE();
        if(base == 0) return 0;

        razaoEletricidade = calRefEmergiaSolarEletricidade() / base;

        return razaoEletricidade;
    }
}
