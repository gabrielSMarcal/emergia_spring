package br.com.example.emergia.models;

import br.com.example.emergia.database.AtributosFixos;

public class CuidadoSolo extends AtributosFixos{

    // Ha = Hectatres
    private double toneladasPorHa;
    private int ano;
    private double areaDevastadaPeloGado;

    public double getToneladasPorHa() {
        return toneladasPorHa;
    } //não sei se isso é necessário

    public CuidadoSolo (double toneladasPorHa, double areaDevastadaPeloGado, int ano) {
        this.areaDevastadaPeloGado = areaDevastadaPeloGado;
        this.toneladasPorHa = toneladasPorHa;
        this.ano = ano;
    }

    public double calcCS() {
        /*getAreaPorHa()*/
        return (toneladasPorHa/ano) * getConversaoToneladaParaGramas() * areaDevastadaPeloGado;
    }

    /*
    o que atualizei:
    - Retirei a variável resul e coloquei apenas return
     */
}
