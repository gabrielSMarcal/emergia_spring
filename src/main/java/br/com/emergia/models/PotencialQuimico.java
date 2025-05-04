package br.com.emergia.models;

import br.com.emergia.database.AtributosFixos;

public class PotencialQuimico extends AtributosFixos {

    // ha = hectares
    private double haFazenda;
    private String uFazenda = "Ha - Hectares";
    private double mediaChuvaAnoMetros; //é por ano em metros
    private String uChuvaMetros = "M³";
    private double resulPotencialQuimico;
    private String uResulPotencialQuimico = "unid/ano";
    private double resulRefEmergiaSolarPotencialQuimico;
    private String uResulRefEmergiaSolarPotencialQuimico = "seJ/unid";

    public PotencialQuimico(double haFazenda, double mediaChuvaAnoMetros){
        this.haFazenda = haFazenda;
        this.mediaChuvaAnoMetros = mediaChuvaAnoMetros;
    }

    public double getHaFazenda() {
        return haFazenda;
    }

    public double getMediaChuva() {
        return mediaChuvaAnoMetros;
    }

    public double calcPQ() {
        resulPotencialQuimico = haFazenda * mediaChuvaAnoMetros * getConversaoHaParaM2() * getKgPorM3() * getEnergiaPorKg();

        return resulPotencialQuimico;
    }

    public double calRefEmergiaSolarPotencialQuimico(){
        resulRefEmergiaSolarPotencialQuimico = calcPQ() * getTransformidadePontencialQuimico();
        return resulRefEmergiaSolarPotencialQuimico;
    }

}
