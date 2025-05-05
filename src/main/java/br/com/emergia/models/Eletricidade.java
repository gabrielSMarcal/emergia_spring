package br.com.emergia.models;

import br.com.emergia.database.AtributosFixos;

public class Eletricidade extends AtributosFixos{

    private double energiaPORKWH;
    private String uEnergiaKWH = "kWH";
    private double resulEletricidade;
    private String uResulEletricidade = "unid/ano";
    private double resulRefEmergiaSolarEletricidade;
    private String uResulRefEmergiaSolarEletricidade = "seJ/unid";

    public Eletricidade (double energiaPORKWH) {
        this.energiaPORKWH = energiaPORKWH;
    }

    public double getEnergiaPorKWH() {
        return energiaPORKWH;
    }

    public double calcE() {
        resulEletricidade = (energiaPORKWH * getMesAno() * getKwhParaKcal()) * getEnergiaPorKcal();
        return resulEletricidade;
    }

    public double calRefEmergiaSolarEletricidade(){
        resulRefEmergiaSolarEletricidade = calcE() * getTransformidadeEletricidade();
        return resulRefEmergiaSolarEletricidade;
    }

    public double calcRazaoEletricidade(){
        double base = calcE();
        if(base == 0) return 0;
        return calRefEmergiaSolarEletricidade() / base;
    }
}
