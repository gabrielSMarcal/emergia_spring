package br.com.example.emergia.models;


import br.com.example.emergia.database.AtributosFixos;

public class Gado extends AtributosFixos {

    private double pesoKgMedia;
    private int numeroAnimais;
    private float anosVidamedia;

    public Gado (double pesoKgMedia, int numeroAnimais, float anosVida){
        this.pesoKgMedia = pesoKgMedia;
        this.numeroAnimais = numeroAnimais;
        this.anosVidamedia = anosVida;
    }

    public double getPesoKgMedia() {
        return pesoKgMedia;
    }

    public int getNumeroAnimais() {
        return numeroAnimais;
    }

    public float getAnosVidamedia() {
        return anosVidamedia;
    }

    public double calcGado() {

        return (pesoKgMedia * numeroAnimais )/ anosVidamedia * getPesoSecoPorAnimal() * getKcalPorGramaCarne() * getJoulesPorKcal() * getGramasPorKg();
    }

    /*
    o que atualizei:
    - Aqui não precisa fazer a cotação do dollar, só precisaria se fosse vender o gado. Este calculo é para saber a média de energia utilizada para à criação do gado.
    - Retirei a variável resul e coloquei apenas return
    - Mudei o getKcalPorGrama para getKcalPorGramaCarne, corrigindo o cálculo
     */
}
