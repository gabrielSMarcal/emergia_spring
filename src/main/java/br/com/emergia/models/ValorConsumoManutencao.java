package br.com.emergia.models;

import br.com.emergia.api.ApiCotacaoDolar;
import br.com.emergia.database.AtributosFixos;

public class ValorConsumoManutencao extends AtributosFixos {

    private double bens;
    private int anos; // não é constante, é em quantos anos o valor em bens foi gasto na fazenda



    public ValorConsumoManutencao (double bens, int anos){
        this.bens = bens;
        this.anos = anos;
    }

    public double getBens() {
        return bens;
    }

    public int getAnos() {
        return anos;
    }

    public double calcBens(){
        try {
            double cotacaoDolar = ApiCotacaoDolar.getCotacaoDolar();
            return (bens / anos) / cotacaoDolar;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao calcular Valor de consumo e manutenção: " + e.getMessage());
        }
    }
}
