package br.com.emergia.models.contribuicaoHumana.operacoesProducao;

import br.com.emergia.database.AtributosFixos;

public class CuidadoSolo extends AtributosFixos{

    // Ha = Hectatres
    private double toneladasPorHa;
    private int ano;

    private double resulCuidadoSolo;
    private double resulRefEmergiaSolarCuidadoSolo;
    private double razaoCuidadoSolo;

    private String unidadeCuidadoSolo = "J";

    public CuidadoSolo() {}

    public double getToneladasPorHa() {
        return toneladasPorHa;
    } //não sei se isso é necessário

    private double areaDevastadaPeloGado;

    public String getUnidadeCuidadoSolo() {
        return unidadeCuidadoSolo;
    }

    public double getResulRefEmergiaSolarCuidadoSolo() {
        return resulRefEmergiaSolarCuidadoSolo;
    }

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

        razaoCuidadoSolo = calRefEmergiaSolarCuidadoSolo() / base;

        return razaoCuidadoSolo;
    }
}
