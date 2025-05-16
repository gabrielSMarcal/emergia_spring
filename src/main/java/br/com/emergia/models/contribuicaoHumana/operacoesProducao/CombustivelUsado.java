package br.com.emergia.models.contribuicaoHumana.operacoesProducao;

import br.com.emergia.database.AtributosFixos;

public class CombustivelUsado extends AtributosFixos {

    private double horasTratorPorAno;
    private double qtdTrator;
    private double litrosPorHora;

    private double resulCombustivelUsado;
    private double resulRefEmergiaSolarCombustivelUsado;
    private double razaoCombustivelUsado;

    private String unidadeCombustivelUsado = "J";

    public CombustivelUsado(double horasTratorPorAno, double qtdTrator, double litrosPorHora) {
        this.horasTratorPorAno = horasTratorPorAno;
        this.litrosPorHora = litrosPorHora;
        this.qtdTrator = qtdTrator;

    }

    public String getUnidadeCombustivelUsado() {
        return unidadeCombustivelUsado;
    }

    public double calCombustivelUsado() {
        resulCombustivelUsado = (horasTratorPorAno * qtdTrator * getJoulesPorTonelada()) / getToneladaPorLitro();
        return resulCombustivelUsado;
    }

    public  double calRefEmergiaSolarCombustivelUsado() {

        resulRefEmergiaSolarCombustivelUsado = calCombustivelUsado() * getTransformidadeCombustivelUsado();
        return resulRefEmergiaSolarCombustivelUsado;
    }

    public double calcRazaoCombustivelUsado() {
        
        double base = calCombustivelUsado();
        if(base == 0) return 0;

        razaoCombustivelUsado = calRefEmergiaSolarCombustivelUsado() / base;

        return razaoCombustivelUsado;
    }

}
