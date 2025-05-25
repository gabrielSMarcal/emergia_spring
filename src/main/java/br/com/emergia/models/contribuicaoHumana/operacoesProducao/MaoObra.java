package br.com.emergia.models.contribuicaoHumana.operacoesProducao;

import br.com.emergia.database.AtributosFixos;

public class MaoObra extends AtributosFixos {

    private int pessoa;
    private double horasTrabalhada;
    private int qtdDiasTrabalhado;
    private int horasAnoReferencia = 2000;

    private double resulMaoObra;
    private double resulRefEmergiaSolarMaoObra;
    private double razaoMaoObra;

    public MaoObra() {}

    public MaoObra (int pessoa, double horasTrabalhada, int qtdDiasTrabalhado) {

        this.pessoa = pessoa;
        this.horasTrabalhada = horasTrabalhada;
        this.qtdDiasTrabalhado = qtdDiasTrabalhado;
    }

    public double getResulRefEmergiaSolarMaoObra() {
        return resulRefEmergiaSolarMaoObra;
    }

    public double calcMO() {
        
        resulMaoObra = ((pessoa * horasTrabalhada * qtdDiasTrabalhado) / horasAnoReferencia) * getEnergiaPessoaAno();
        return resulMaoObra;
    }

    public double calRefEmergiaSolarMaoObra () {

        resulRefEmergiaSolarMaoObra = calcMO() * getTransformidadeMaoObra();
        return resulRefEmergiaSolarMaoObra;
    }

    public double calcRazaoMaoObra() {

        double base = calcMO();
        if(base == 0) return 0;

        razaoMaoObra = calRefEmergiaSolarMaoObra() / base;

        return razaoMaoObra;
    }
}

