package br.com.emergia.models;

import br.com.emergia.database.AtributosFixos;

public class AguaUsada extends AtributosFixos {

    private double litroPorDiaAnimal;
    private String lAnimal = "L";
    private int qtdAnimal;
    private String uAnimal = "U";
    private double litrosAdicionalDia;
    private String lAnimalAdicinal = "L";
    private double resulAguaUsada;
    private String uResulAguaUsada = "unid/ano";
    private double resulRefEmergiaSolarAguaUsada;
    private String uResulRefEmergiaSolarAguaUsada = "seJ/unid";

    public AguaUsada (double litroPorDiaAnimal, int qtdAnimal, double litrosAdicionalDia){
        this.litroPorDiaAnimal= litroPorDiaAnimal;
        this.qtdAnimal = qtdAnimal;
        this.litrosAdicionalDia = litrosAdicionalDia;
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

    public double calcAU (){

        double totalLitrosDia = litroPorDiaAnimal * qtdAnimal + litrosAdicionalDia;
        resulAguaUsada = totalLitrosDia * getDiasAno() * getQtdEnergiaPorKilo(); // energia ano

        return resulAguaUsada;
    }

    public double calRefEmergiaSolarAguaUsada(){
        resulRefEmergiaSolarAguaUsada = calcAU() * getTransformidadeAguaUsada();
        return resulRefEmergiaSolarAguaUsada;
    }

}
