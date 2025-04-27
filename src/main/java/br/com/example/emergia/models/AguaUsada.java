package br.com.example.emergia.models;

import br.com.example.emergia.database.AtributosFixos;

public class AguaUsada extends AtributosFixos {

    private double litroPorDiaAnimal;
    private int qtdAnimal;
    private double litrosAdicionalDia;

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

        double totalLitrosDia = (litroPorDiaAnimal * qtdAnimal) + litrosAdicionalDia;
        return totalLitrosDia * getDiasAno() * getQtdEnergiaPorKilo(); // energia ano
    }
    /*
    o que atualizei:
    - Retirei a variável resul e coloquei apenas return
     */

}
