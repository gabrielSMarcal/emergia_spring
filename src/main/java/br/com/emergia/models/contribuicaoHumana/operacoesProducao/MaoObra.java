package br.com.emergia.models.contribuicaoHumana.operacoesProducao;

import br.com.emergia.database.AtributosFixos;

public class MaoObra extends AtributosFixos{

    private int pessoa;
    private String uPessoa = "U";
    private double horasTrabalhada;
    private String uHorasTrabalhada = "H";
    private int qtdDiasTrabalhado;
    private String uQtdDiasTrabalhado = "Dias";
    private int horasAnoReferencia = 2000;
    private double resulMaoObra;
    private String uResulMaoObra = "unid/ano";
    private double resulRefEmergiaSolarMaoObra;
    private String uResulRefEmergiaSolarMaoObra = "seJ/unid";

    public MaoObra (int pessoa, double horasTrabalhada, int qtdDiasTrabalhado){
        this.pessoa = pessoa;
        this.horasTrabalhada = horasTrabalhada;
        this.qtdDiasTrabalhado = qtdDiasTrabalhado;
    }

    public int getPessoas() {
        return pessoa;
    }

    public double getHorasTrabalhadas() {
        return horasTrabalhada;
    }

    public int getQtdDiasTrabalhado() {
        return qtdDiasTrabalhado;
    }

    public double calcMO() {
        resulMaoObra = ((pessoa * horasTrabalhada * qtdDiasTrabalhado) / horasAnoReferencia) * getEnergiaPessoaAno();
        return resulMaoObra;
    }

    public double calRefEmergiaSolarMaoObra (){
        resulRefEmergiaSolarMaoObra = calcMO() * getTransformidadeMaoObra();
        return resulRefEmergiaSolarMaoObra;
    }

    public double calcRazaoMaoObra(){
        double base = calcMO();
        if(base == 0) return 0;
        return calRefEmergiaSolarMaoObra() / base;
    }
}

