package br.com.emergia.models.contribuicaoAmbiental.energiaRenovavel;

import br.com.emergia.database.AtributosFixos;

public class PotencialQuimico extends AtributosFixos {

    // ha = hectares
    private double haFazenda;
    private double mediaChuvaAnoMetros; //é por ano em metros

    private double resulPotencialQuimico;
    private double resulRefEmergiaSolarPotencialQuimico;
    private double razaoPotencialQuimico;

    public PotencialQuimico() {}

    public PotencialQuimico(double haFazenda, double mediaChuvaAnoMetros) {
        this.haFazenda = haFazenda;
        this.mediaChuvaAnoMetros = mediaChuvaAnoMetros;
    }

    public double getResulRefEmergiaSolarPotencialQuimico() {
        return resulRefEmergiaSolarPotencialQuimico;
    }

    public String getUnidadePotencialQuimico() {
        return unidadePotencialQuimico;
    }

    public double calcPQ() {
        resulPotencialQuimico = haFazenda * mediaChuvaAnoMetros * getConversaoHaParaM2() * getKgPorM3() * getEnergiaPorKg();
        return resulPotencialQuimico;
    }

    public double calRefEmergiaSolarPotencialQuimico() {

        resulRefEmergiaSolarPotencialQuimico = calcPQ() * getTransformidadePontencialQuimico();
        return resulRefEmergiaSolarPotencialQuimico;
    }

    public double calcRazaoPotencialQuimico() {
        
        double base = calcPQ();
        if(base == 0) return 0;

        razaoPotencialQuimico = calRefEmergiaSolarPotencialQuimico() / base;
        
        return razaoPotencialQuimico;
    }

}
