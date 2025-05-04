package br.com.emergia.models;

import br.com.emergia.api.ApiCotacaoDolar;
import br.com.emergia.database.AtributosFixos;

public class Maquinarios extends AtributosFixos {

    private double qtdHoraTrator;
    private String QtdHorasTrator = "H";
    private double valorHoraTrator;
    private String uValorHoraTrator = "R$";
    private double resulMaquinario;
    private String uResulMaquinario = "unid/ano";
    private double resulRefEmergiaSolarMaquinario;
    private String uResulRefEmergiaSolarMaquinario = "seJ/unid";

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
            resulMaquinario = (qtdHoraTrator * valorHoraTrator) / cotacaoDolar;
            return resulMaquinario;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao calcular Maquinarios: " + e.getMessage());
        }
    }

    public double calRefEmergiaSolarMaquinario(){
        resulRefEmergiaSolarMaquinario = calcM() * getTransformidadeMaquinario();
        return resulRefEmergiaSolarMaquinario;
    }
}
