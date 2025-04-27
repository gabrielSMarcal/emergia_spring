package br.com.example.emergia.models;

import br.com.example.emergia.database.AtributosFixos;

public class CombustivelUsado extends AtributosFixos{

    private double horasTratorPorAno;
    private double qtdTrator;
    private double litrosPorHora;

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

    public double calcCombustivelUsado() {
        return (horasTratorPorAno * qtdTrator * getJoulesPorTonelada()) / getToneladaPorLitro();
    }
    /*
    o que atualizei:
    - Retirei a variável resul e coloquei apenas return
     */
}