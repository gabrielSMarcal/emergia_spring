package br.com.emergia.models;

import br.com.emergia.database.AtributosFixos;

public class Eletricidade extends AtributosFixos{

    private double energiaPORKWH;


    public Eletricidade (double energiaPORKWH) {
        this.energiaPORKWH = energiaPORKWH;
    }

    public double getEnergiaPorKWH() {
        return energiaPORKWH;
    }

    public double calcE() {
        return (energiaPORKWH * getMesAno() * getKwhParaKcal()) * getEnergiaPorKcal();
    }
    /*
    o que atualizei:
    - Retirei a variável resul e coloquei apenas return
     */

}
