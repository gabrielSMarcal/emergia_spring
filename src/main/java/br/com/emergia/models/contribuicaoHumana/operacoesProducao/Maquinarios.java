package br.com.emergia.models.contribuicaoHumana.operacoesProducao;

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

        double dolarProvisorio = 6.7;

        try {
            double cotacaoDolar = dolarProvisorio;
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

    public double calcRazaoMaquinario(){
        double base = calcM();
        if(base == 0) return 0;
        return calRefEmergiaSolarMaquinario() / base;
    }
}
