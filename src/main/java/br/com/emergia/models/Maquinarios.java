package br.com.emergia.models;

import br.com.emergia.api.ApiCotacaoDolar;
import br.com.emergia.database.AtributosFixos;

public class Maquinarios extends AtributosFixos {

    private double qtdHoraTrator;
    private double valorHoraTrator;

    public Maquinarios(double qtdHoraTrator, double valorHoraTrator) {
        this.qtdHoraTrator = qtdHoraTrator;
        this.valorHoraTrator = valorHoraTrator;
    }

    public double getQtdHorasTrator() {
        return qtdHoraTrator;
    }

    public double getValorHoraTrator() {
        return valorHoraTrator;
    }

    public double calcM() {

        try {
            double cotacaoDolar = ApiCotacaoDolar.getCotacaoDolar();
            return (qtdHoraTrator * valorHoraTrator) / cotacaoDolar;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao calcular Maquinarios: " + e.getMessage());
        }
    }
}
