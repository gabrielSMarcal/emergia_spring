package br.com.emergia.models.contribuicaoHumana.operacoesProducao;


import br.com.emergia.database.AtributosFixos;

public class Gado extends AtributosFixos {

    private double pesoKgMedia;
    private int numeroAnimais;
    private float anosVidamedia;

    private double resulGado;
    private double resulRefEmergiaSolarGado;
    private double razaoGado;

    private String unidadeGado = "J";
    

    public Gado (double pesoKgMedia, int numeroAnimais, float anosVida) {

        this.pesoKgMedia = pesoKgMedia;
        this.numeroAnimais = numeroAnimais;
        this.anosVidamedia = anosVida;
    }

    public String getUnidadeGado() {
        return unidadeGado;
    }

    public double calcG() {

        resulGado = (pesoKgMedia * numeroAnimais) / anosVidamedia * getPesoSecoPorAnimal() * getKcalPorGramaCarne() * getJoulesPorKcal() * getGramasPorKg();
        return resulGado;
    }

    public double calRefEmergiaSolarGado() {

        resulRefEmergiaSolarGado = calcG() * getTransformidadeGado();
        return resulRefEmergiaSolarGado;
    }

    public double calcRazaoGado() {
        
        double base = calcG();
        if(base == 0) return 0;

        razaoGado = calRefEmergiaSolarGado() / base;

        return razaoGado;
    }
}
