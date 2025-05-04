package br.com.emergia.database;

public class AtributosFixos {

    // Globais
    int diasAno = 365;
    int mesAno = 12;
    double conversaoToneladaParaGramas = 1.00E06;

    // AguaUsada
    double qtdEnergiaPorKilo = 4.94E03;
    double transformidadeAguaUsada = 4.11e4;

    // Bens
    int anos = 20;

    // CombustivelUsado
    double joulesPorTonelada = 4.40E10;
    double toneladaPorLitro = 1.00E03;
    double transformidadeCombustivelUsado = 5.30e4;

    // CuidadoSolo
    double transformidadeCuidadoSolo = 2.63e9;

    // Eletricidade
    int kwhParaKcal = 860;
    int energiaPorKcal = 4186;
    private double transformidadeEletricidade = 2.00e5;
    // MaoObra
    double energiaPessoaAno = 4.6E09;
    double transformidadeMaoObra = 6.50e5;

    // PerdaSolo
    double gramasMOporGramasSolo = 0.07;
    double kcalPorGrama = 3.6;
    double joulesPorKcal = 4186;
    double transformidadePerdaSolo = 6.30e4;

    // PotencialQuimico
    double conversaoM2porHa = 10000;
    double kgPorM3 = 1000;
    double energiaPorKg = 4940;
    double transformidadePontencialQuimico = 1.50e4;

    // ProducaoLeite
    double litroParaGrama = 1000;
    double kcalPorGramaLeite = 0.625F;
    double joulesPorKcalLeite = 4186;
    double transformidadeProducaoleite = 2.04e6;

    // Gado
    double pesoSecoPorAnimal = 0.2;
    double kcalPorGramaCarne = 7;
    int gramasPorKg = 1000;
    double transformidadeGado = 1.72e6;

    // Racao
    double transformidadeRacao = 6.08e12;

    // Maquinario

    double transformidadeMaquinario = 6.08e12;

    public double getTransformidadeProducaoleite() {
        return transformidadeProducaoleite;
    }

    public double getTransformidadeGado() {
        return transformidadeGado;
    }

    public double getTransformidadePontencialQuimico() {
        return transformidadePontencialQuimico;
    }

    public double getTransformidadePerdaSolo(){
        return transformidadePerdaSolo;
    }

    public double getTransformidadeMaquinario (){
        return transformidadeMaquinario;
    }

    public double getTransformidadeCuidadoSolo() {
        return transformidadeCuidadoSolo;
    }

    public double getTransformidadeRacao() {
        return transformidadeRacao;
    }

    public double getTransformidadeAguaUsada() {
        return transformidadeAguaUsada;
    }

    public double getTransformidadeCombustivelUsado() {
        return transformidadeCombustivelUsado;
    }

    public double getTransformidadeEletricidade(){
        return transformidadeEletricidade;}

    public double getTransformidadeMaoObra(){
        return transformidadeMaoObra;
    }

    public  double getPesoSecoPorAnimal(){
        return pesoSecoPorAnimal;
    }

    public double getKcalPorGramaCarne(){
        return kcalPorGramaCarne;
    }

    public double getGramasPorKg(){
        return gramasPorKg;
    }

    public int getDiasAno() {
        return diasAno;
    }

    public int getMesAno() {
        return mesAno;
    }

    public double getQtdEnergiaPorKilo() {
        return qtdEnergiaPorKilo;
    }

    public int getAnos() {
        return anos;
    }

    public double getJoulesPorTonelada() {
        return joulesPorTonelada;
    }

    public double getToneladaPorLitro() {
        return toneladaPorLitro;
    }

    public int getKwhParaKcal() {
        return kwhParaKcal;
    }

    public int getEnergiaPorKcal() {
        return energiaPorKcal;
    }

    public double getEnergiaPessoaAno() {
        return energiaPessoaAno;
    }

    public double getConversaoToneladaParaGramas() {
        return conversaoToneladaParaGramas;
    }

    public double getGramasMOporGramasSolo() {
        return gramasMOporGramasSolo;
    }

    public double getKcalPorGrama() {
        return kcalPorGrama;
    }

    public double getJoulesPorKcal() {
        return joulesPorKcal;
    }

    public double getConversaoHaParaM2() {
        return conversaoM2porHa;
    }

    public double getKgPorM3() {
        return kgPorM3;
    }

    public double getEnergiaPorKg() {
        return energiaPorKg;
    }

    public double getLitroParaGrama() {
        return litroParaGrama;
    }

    public double getKcalPorGramaLeite() {
        return kcalPorGramaLeite;
    }

    public double getJoulesPorKcalLeite() {
        return joulesPorKcalLeite;
    }

}
