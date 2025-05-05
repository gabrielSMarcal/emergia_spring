package br.com.emergia.models;

import br.com.emergia.database.AtributosFixos;

public class CuidadoSolo extends AtributosFixos{

    // Ha = Hectatres
    private double toneladasPorHa;
    private String uToneladasHa = "T";
    private int ano;
    private String uAno = "Ano";
    private double resulCuidadoSolo;
    private String uResulCuidadoSolo = "unid/ano";
    private double resulRefEmergiaSolarCuidadoSolo;
    private String uResulRefEmergiaSolarCuidadoSolo = "seJ/unid";


    public double getToneladasPorHa() {
        return toneladasPorHa;
    } //não sei se isso é necessário

    private double areaDevastadaPeloGado;

    public CuidadoSolo (double toneladasPorHa, double areaDevastadaPeloGado, int ano) {
        this.areaDevastadaPeloGado = areaDevastadaPeloGado;
        this.toneladasPorHa = toneladasPorHa;
        this.ano = ano;
    }

    public double calcCS() {
        resulCuidadoSolo = (toneladasPorHa/ano) * getConversaoToneladaParaGramas() * areaDevastadaPeloGado;
        return resulCuidadoSolo;
    }

    public double calRefEmergiaSolarCuidadoSolo(){
        resulRefEmergiaSolarCuidadoSolo = calcCS() * getTransformidadeCuidadoSolo();
        return resulRefEmergiaSolarCuidadoSolo;
    }

    public double calcRazaoCuidadoSolo(){
        double base = calcCS();
        if(base == 0) return 0;
        return calRefEmergiaSolarCuidadoSolo() / base;
    }
}
