package br.com.emergia.models;


import br.com.emergia.database.AtributosFixos;

public class Gado extends AtributosFixos {

    private double pesoKgMedia;
    private String uPesoKgMedia = "Kg";
    private int numeroAnimais;
    private String uNumeroAnimal = "U";
    private float anosVidamedia;
    private String uAnosVida = "Ano";
    private double resulGado;
    private String uResulGado = "unid/ano";
    private double resulRefEmergiaSolarGado;
    private String uResulRefEmergiaSolarGado = "seJ/unid";

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

    public double calcG() {

        resulGado = (pesoKgMedia * numeroAnimais )/ anosVidamedia * getPesoSecoPorAnimal() * getKcalPorGramaCarne() * getJoulesPorKcal() * getGramasPorKg();

        return resulGado;
    }

    public double calRefEmergiaSolarGado(){
        resulRefEmergiaSolarGado = calcG() * getTransformidadeGado();
        return resulRefEmergiaSolarGado;
    }
}
