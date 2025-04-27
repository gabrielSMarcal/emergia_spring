package br.com.example.emergia.models;

import br.com.example.emergia.database.AtributosFixos;

public class PerdaSolo extends AtributosFixos {

    private double perdaDeSoloToneladasPorAno;

    public PerdaSolo(double perdaDeSoloToneladasPorAno){
        this.perdaDeSoloToneladasPorAno = perdaDeSoloToneladasPorAno;
    }

    public double getPerdaDeSoloToneladasPorAno() {
        return perdaDeSoloToneladasPorAno;
    }

    public double calcPS(){
        return perdaDeSoloToneladasPorAno * getConversaoToneladaParaGramas()
                * getGramasMOporGramasSolo() * getKcalPorGrama() * getJoulesPorKcal();
    }
    /*
    o que atualizei:
    - Retirei a variável resul e coloquei apenas return
     */
}
