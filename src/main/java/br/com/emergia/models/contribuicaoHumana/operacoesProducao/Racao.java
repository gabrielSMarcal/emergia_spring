package br.com.emergia.models.contribuicaoHumana.operacoesProducao;

import br.com.emergia.database.AtributosFixos;

public class Racao extends AtributosFixos{

    private int saca;
    private double valorSaca;

    private double resulRacao;
    private double resulRefEmergiaSolarRacao;
    private double razaoRacao;

    private String unidadeRacao = "t";

    public Racao () {}

    public Racao (int saca, double valorSaca) {

        this.saca = saca;
        this.valorSaca = valorSaca;
    }

    public String getUnidadeRacao() {
        return unidadeRacao;
    }

    public double getResulRefEmergiaSolarRacao() {
        return resulRefEmergiaSolarRacao;
    }

    public double calcR() {

        double dolarProvisorio = 6.7;

        try {
            double cotacaoDolar = dolarProvisorio;
            resulRacao = (saca * valorSaca * getMesAno()) / cotacaoDolar;
            return resulRacao;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao calcular Racao: " + e.getMessage());
        }
    }

    public  double calRefEmergiaSolarRacao() {

        resulRefEmergiaSolarRacao = calcR() * getTransformidadeRacao();
        return resulRefEmergiaSolarRacao;
    }

    public double calcRazaoRacao() {

        double base = calcR();
        if(base == 0) return 0;

        razaoRacao = calRefEmergiaSolarRacao() / base;

        return razaoRacao;
    }
}

