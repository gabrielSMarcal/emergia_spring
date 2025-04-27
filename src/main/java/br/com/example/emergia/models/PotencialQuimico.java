package br.com.example.emergia.models;

import br.com.example.emergia.database.AtributosFixos;

public class PotencialQuimico extends AtributosFixos {

    // ha = hectares
    private double haFazenda;
    private double mediaChuvaAnoMetros; //é por ano em metros

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
        return haFazenda * mediaChuvaAnoMetros * getConversaoHaParaM2() * getKgPorM3() * getEnergiaPorKg();
    }
    /*
    o que atualizei:
    - Retirei a variável resul e coloquei apenas return
     */
}