package br.com.emergia.models;

import br.com.emergia.api.ApiCotacaoDolar;
import br.com.emergia.database.AtributosFixos;

public class Racao extends AtributosFixos{

    private int saca;
    private String uSaca = "U";
    private double valorSaca;
    private String uValorSaca = "R$";
    private double resulRacao;
    private String uResulRacao = "unid/ano";
    private double resulRefEmergiaSolarRacao;
    private String uResulRefEmergiaSolarRacao = "seJ/unid";

    public Racao (int saca, double valorSaca){
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
            resulRacao = (saca * valorSaca * getMesAno()) / cotacaoDolar;
            return resulRacao;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao calcular Racao: " + e.getMessage());
        }
    }

    public  double calRefEmergiaSolarRacao(){
        resulRefEmergiaSolarRacao = calcR() * getTransformidadeRacao();
        return resulRefEmergiaSolarRacao;
    }
}

