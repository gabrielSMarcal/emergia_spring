package br.com.emergia.models.contribuicaoAmbiental.energiaNaoRenovavel;

import br.com.emergia.database.AtributosFixos;

public class PerdaSolo extends AtributosFixos {

    private double perdaDeSoloToneladasPorAno;

    private double resulPerdaSolo;
    private double resulRefEmergiaSolarPerdaSolo;
    private double razaoPerdaSolo;

    public PerdaSolo() {}

    public PerdaSolo(double perdaDeSoloToneladasPorAno) {
        this.perdaDeSoloToneladasPorAno = perdaDeSoloToneladasPorAno;
    }

    public double getPerdaDeSoloToneladasPorAno() {
        return perdaDeSoloToneladasPorAno;
    }

    public double getResulRefEmergiaSolarPerdaSolo() {
        return resulRefEmergiaSolarPerdaSolo;
    }

    public double calcPS() {

        resulPerdaSolo = perdaDeSoloToneladasPorAno * getConversaoToneladaParaGramas()
                * getGramasMOporGramasSolo() * getKcalPorGrama() * getJoulesPorKcal();
        return resulPerdaSolo;
    }

    public double calRefEmergiaSolarPerdaSolo() {

        resulRefEmergiaSolarPerdaSolo = calcPS() * getTransformidadePerdaSolo();
        return resulRefEmergiaSolarPerdaSolo;
    }

    public double calcRazaoPerdaSolo() {
        
        double base = calcPS();
        if(base == 0) return 0;

        razaoPerdaSolo = calRefEmergiaSolarPerdaSolo() / base;

        return razaoPerdaSolo;
    }
}
