package br.com.emergia.models;

import br.com.emergia.database.AtributosFixos;

public class CombustivelUsado extends AtributosFixos{

    private double horasTratorPorAno;
    private String uHorasTrator = "Hrs";
    private double qtdTrator;
    private String uQtdTrator = "U";
    private double litrosPorHora;
    private String uLitrosCombustivelHora = "L";
    private double resulCombustivelUsado;
    private String uResulCombustivelUsado = "Unid/ano";
    private double resulRefEmergiaSolarCombustivelUsado;
    private String uResulRefEmergiaSolarCombustivelUsado = "seJ/unid";

    public CombustivelUsado(double horasTratorPorAno, double qtdTrator, double litrosPorHora) {
        this.horasTratorPorAno = horasTratorPorAno;
        this.litrosPorHora = litrosPorHora;
        this.qtdTrator = qtdTrator;

    }

    public double getHorasTratorPorAno() {
        return horasTratorPorAno;
    }

    public double getQtdTrator() {
        return qtdTrator;
    }

    public double getLitrosPorHora() {
        return litrosPorHora;
    }

    public double calCU() {
        resulCombustivelUsado = (horasTratorPorAno * qtdTrator * getJoulesPorTonelada()) / getToneladaPorLitro();
        return resulCombustivelUsado;
    }

    public  double calRefEmergiaSolarCombustivelUsado(){
        resulRefEmergiaSolarCombustivelUsado = calCU() * getTransformidadeCombustivelUsado();
        return resulRefEmergiaSolarCombustivelUsado;
    }

}
