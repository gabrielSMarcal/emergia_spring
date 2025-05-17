package br.com.emergia.models.contribuicaoAmbiental.energiaNaoRenovavel;

import br.com.emergia.database.AtributosFixos;

public class AguaUsada extends AtributosFixos {

    private double litroPorDiaAnimal;
    private int qtdAnimal;
    private double litrosAdicionalDia;

    private double resulAguaUsada;
    private double resulRefEmergiaSolarAguaUsada;
    private double razaoAguaUsada;

    private String unidadeAguaUsada = "J";

    public AguaUsada() {}

    public AguaUsada (double litroPorDiaAnimal, int qtdAnimal, double litrosAdicionalDia) {
        this.litroPorDiaAnimal = litroPorDiaAnimal;
        this.qtdAnimal = qtdAnimal;
        this.litrosAdicionalDia = litrosAdicionalDia;
    }

    public String getUnidadeAguaUsada() {
        return unidadeAguaUsada;
    }

    public double getLitroPorDiaAnimal() {
        return litroPorDiaAnimal;
    }

    public int getQtdAnimal() {
        return qtdAnimal;
    }

    public double getLitrosAdicionalDia() {
        return litrosAdicionalDia;
    }

    public double getResulRefEmergiaSolarAguaUsada() {
        return resulRefEmergiaSolarAguaUsada;
    }

    public double calcAU () {

        double totalLitrosDia = litroPorDiaAnimal * qtdAnimal + litrosAdicionalDia;
        resulAguaUsada = totalLitrosDia * getDiasAno() * getQtdEnergiaPorKilo(); // energia ano

        return resulAguaUsada;
    }

    public double calRefEmergiaSolarAguaUsada() {

        resulRefEmergiaSolarAguaUsada = calcAU() * getTransformidadeAguaUsada();
        return resulRefEmergiaSolarAguaUsada;
    }

    public double calcRazaoAguaUsada() {
        
        double base = calcAU();
        if(base == 0) return 0;

        razaoAguaUsada = calRefEmergiaSolarAguaUsada() / base;

        return razaoAguaUsada;
    }

}
