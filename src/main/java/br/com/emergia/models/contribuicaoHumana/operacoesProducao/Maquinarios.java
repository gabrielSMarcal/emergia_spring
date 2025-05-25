package br.com.emergia.models.contribuicaoHumana.operacoesProducao;

import br.com.emergia.database.AtributosFixos;
import br.com.emergia.services.ApiCotacaoDolar;

public class Maquinarios extends AtributosFixos {

    private double qtdHoraTrator;
    private double valorHoraTrator;

    private double resulMaquinario;
    private double resulRefEmergiaSolarMaquinario;
    private double razaoMaquinario;

    public Maquinarios() {}

    public Maquinarios(double qtdHoraTrator, double valorHoraTrator) {
        this.qtdHoraTrator = qtdHoraTrator;
        this.valorHoraTrator = valorHoraTrator;
    }

    public double getResulRefEmergiaSolarMaquinario() {
        return resulRefEmergiaSolarMaquinario;
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

    public double calRefEmergiaSolarMaquinario() {

        resulRefEmergiaSolarMaquinario = calcM() * getTransformidadeMaquinario();
        return resulRefEmergiaSolarMaquinario;
    }

    public double calcRazaoMaquinario() {

        double base = calcM();
        if(base == 0) return 0;

        razaoMaquinario = calRefEmergiaSolarMaquinario() / base;

        return razaoMaquinario;
    }
}
