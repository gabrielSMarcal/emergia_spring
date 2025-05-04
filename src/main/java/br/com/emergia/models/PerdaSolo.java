package br.com.emergia.models;

import br.com.emergia.database.AtributosFixos;

public class PerdaSolo extends AtributosFixos {

    private double perdaDeSoloToneladasPorAno;
    private String tPerdadeSolo = "T";
    private double resulPerdaSolo;
    private String uResulPerdaSolo = "unid/ano";
    private double resulRefEmergiaSolarPerdaSolo;
    private String uResulRefEmergiaSolarPerdaSolo = "seJ/unid";


    public PerdaSolo(double perdaDeSoloToneladasPorAno){
        this.perdaDeSoloToneladasPorAno = perdaDeSoloToneladasPorAno;
    }

    public double getPerdaDeSoloToneladasPorAno() {
        return perdaDeSoloToneladasPorAno;
    }

    public double calcPS(){
        resulPerdaSolo = perdaDeSoloToneladasPorAno * getConversaoToneladaParaGramas()
                * getGramasMOporGramasSolo() * getKcalPorGrama() * getJoulesPorKcal();

        return  resulPerdaSolo;
    }

    public double calRefEmergiaSolarPerdaSolo(){
        resulRefEmergiaSolarPerdaSolo = calcPS() * getTransformidadePerdaSolo();
        return resulRefEmergiaSolarPerdaSolo;
    }
}
