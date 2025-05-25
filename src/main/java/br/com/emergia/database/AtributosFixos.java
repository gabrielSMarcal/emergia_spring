package br.com.emergia.database;

public class AtributosFixos {

    // Globais
    private int diasAno = 365;
    private int mesAno = 12;
    private double conversaoToneladaParaGramas = 1.00E06;

    // AguaUsada
    private double qtdEnergiaPorKilo = 4.94E03;
    private double transformidadeAguaUsada = 4.11e4;

    // Bens
    private int anos = 20;

    // CombustivelUsado
    private double joulesPorTonelada = 4.40E10;
    private double toneladaPorLitro = 1.00E03;
    private double transformidadeCombustivelUsado = 5.30e4;

    // CuidadoSolo
    private double transformidadeCuidadoSolo = 2.63e9;

    // Eletricidade
    private int kwhParaKcal = 860;
    private int energiaPorKcal = 4186;
    private double transformidadeEletricidade = 2.00e5;
    // MaoObra
    private double energiaPessoaAno = 4.6E09;
    private double transformidadeMaoObra = 6.50e5;

    // PerdaSolo
    private double gramasMOporGramasSolo = 0.07;
    private double kcalPorGrama = 3.6;
    private double joulesPorKcal = 4186;
    private double transformidadePerdaSolo = 6.30e4;

    // PotencialQuimico
    private double conversaoM2porHa = 10000;
    private double kgPorM3 = 1000;
    private double energiaPorKg = 4940;
    private double transformidadePontencialQuimico = 1.50e4;

    // ProducaoLeite
    private double litroParaGrama = 1000;
    private double kcalPorGramaLeite = 0.625F;
    private double joulesPorKcalLeite = 4186;
    private double transformidadeProducaoleite = 2.04e6;

    // Gado
    private double pesoSecoPorAnimal = 0.2;
    private double kcalPorGramaCarne = 7;
    private int gramasPorKg = 1000;
    private double transformidadeGado = 1.72e6;

    // Racao
    private double transformidadeRacao = 6.08e12;

    // Maquinario
    private double transformidadeMaquinario = 6.08e12;

    // Getters
    public double getTransformidadeProducaoleite() {return transformidadeProducaoleite;}
    public double getTransformidadeGado() {return transformidadeGado;}
    public double getTransformidadePontencialQuimico() {return transformidadePontencialQuimico;}
    public double getTransformidadePerdaSolo(){return transformidadePerdaSolo;}
    public double getTransformidadeMaquinario (){return transformidadeMaquinario;}
    public double getTransformidadeCuidadoSolo() {return transformidadeCuidadoSolo;}
    public double getTransformidadeRacao() {return transformidadeRacao;}
    public double getTransformidadeAguaUsada() {return transformidadeAguaUsada;}
    public double getTransformidadeCombustivelUsado() {return transformidadeCombustivelUsado;}
    public double getTransformidadeEletricidade(){return transformidadeEletricidade;}
    public double getTransformidadeMaoObra(){return transformidadeMaoObra;}
    public double getPesoSecoPorAnimal(){return pesoSecoPorAnimal;}
    public double getKcalPorGramaCarne(){return kcalPorGramaCarne;}
    public double getGramasPorKg(){return gramasPorKg;}
    public int getDiasAno() {return diasAno;}
    public int getMesAno() {return mesAno;}
    public double getQtdEnergiaPorKilo() {return qtdEnergiaPorKilo;}
    public int getAnos() {return anos;}
    public double getJoulesPorTonelada() {return joulesPorTonelada;}
    public double getToneladaPorLitro() {return toneladaPorLitro;}
    public int getKwhParaKcal() {return kwhParaKcal;}
    public int getEnergiaPorKcal() {return energiaPorKcal;}
    public double getEnergiaPessoaAno() {return energiaPessoaAno;}
    public double getConversaoToneladaParaGramas() {return conversaoToneladaParaGramas;}
    public double getGramasMOporGramasSolo() {return gramasMOporGramasSolo;}
    public double getKcalPorGrama() {return kcalPorGrama;}
    public double getJoulesPorKcal() {return joulesPorKcal;}
    public double getConversaoHaParaM2() {return conversaoM2porHa;}
    public double getKgPorM3() {return kgPorM3;}
    public double getEnergiaPorKg() {return energiaPorKg;}
    public double getLitroParaGrama() {return litroParaGrama;}
    public double getKcalPorGramaLeite() {return kcalPorGramaLeite;}
    public double getJoulesPorKcalLeite() {return joulesPorKcalLeite;}
}
