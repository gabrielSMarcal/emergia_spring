package br.com.example.emergia.models;

import br.com.example.emergia.api.ApiCotacaoDolar;
import br.com.example.emergia.database.AtributosFixos;

public class Racao extends AtributosFixos{

    private int saca;
    private float valorSaca;

    public Racao (int saca, float valorSaca){
        this.saca = saca;
        this.valorSaca = valorSaca;
    }

    public int getSaca() {
        return saca;
    }

    public double getValorSaca() {
        return valorSaca;
    }

    public double calcR() {

        try {
            double cotacaoDolar = ApiCotacaoDolar.getCotacaoDolar();
            return (saca * valorSaca * getMesAno()) / cotacaoDolar;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao calcular Racao: " + e.getMessage());
        }
    }
}
