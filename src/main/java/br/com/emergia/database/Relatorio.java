package br.com.emergia.database;

import jakarta.persistence.*;

@Entity
@Table(name = "relatorio")
public class Relatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeDaFazenda;

    // Cuidado Solo
    private Double calcCuidadoSolo;
    private Double refCuidadoSolo;
    private Double razaoCuidadoSolo;

    // Potencial Químico
    private Double calcPotencialQuimico;
    private Double refPotencialQuimico;
    private Double razaoPotencialQuimico;

    // Produção de Leite
    private Double calcProducaoLeite;
    private Double refProducaoLeite;
    private Double razaoProducaoLeite;

    // Ração
    private Double calcRacao;
    private Double refRacao;
    private Double razaoRacao;

    // Perda Solo
    private Double calcPerdaSolo;
    private Double refPerdaSolo;
    private Double razaoPerdaSolo;

    // Água Usada
    private Double calcAguaUsada;
    private Double refAguaUsada;
    private Double razaoAguaUsada;

    // Combustível Usado
    private Double calcCombustivelUsado;
    private Double refCombustivelUsado;
    private Double razaoCombustivelUsado;

    // Eletricidade
    private Double calcEletricidade;
    private Double refEletricidade;
    private Double razaoEletricidade;

    // Gado
    private Double calcGado;
    private Double refGado;
    private Double razaoGado;

    // Maquinários
    private Double calcMaquinarios;
    private Double refMaquinarios;
    private Double razaoMaquinarios;

    // Mão de Obra
    private Double calcMaoObra;
    private Double refMaoObra;
    private Double razaoMaoObra;

    // Valor Consumo/Manutenção (Bens)
    private Double calcBens;
    private Double refBens;
    private Double razaoBens;

    // Getters e setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNomeDaFazenda() { return nomeDaFazenda; }
    public void setNomeDaFazenda(String nomeDaFazenda) { this.nomeDaFazenda = nomeDaFazenda; }

    public Double getCalcCuidadoSolo() { return calcCuidadoSolo; }
    public void setCalcCuidadoSolo(Double calcCuidadoSolo) { this.calcCuidadoSolo = calcCuidadoSolo; }

    public Double getRefCuidadoSolo() { return refCuidadoSolo; }
    public void setRefCuidadoSolo(Double refCuidadoSolo) { this.refCuidadoSolo = refCuidadoSolo; }

    public Double getRazaoCuidadoSolo() { return razaoCuidadoSolo; }
    public void setRazaoCuidadoSolo(Double razaoCuidadoSolo) { this.razaoCuidadoSolo = razaoCuidadoSolo; }

    public Double getCalcPotencialQuimico() { return calcPotencialQuimico; }
    public void setCalcPotencialQuimico(Double calcPotencialQuimico) { this.calcPotencialQuimico = calcPotencialQuimico; }

    public Double getRefPotencialQuimico() { return refPotencialQuimico; }
    public void setRefPotencialQuimico(Double refPotencialQuimico) { this.refPotencialQuimico = refPotencialQuimico; }

    public Double getRazaoPotencialQuimico() { return razaoPotencialQuimico; }
    public void setRazaoPotencialQuimico(Double razaoPotencialQuimico) { this.razaoPotencialQuimico = razaoPotencialQuimico; }

    public Double getCalcProducaoLeite() { return calcProducaoLeite; }
    public void setCalcProducaoLeite(Double calcProducaoLeite) { this.calcProducaoLeite = calcProducaoLeite; }

    public Double getRefProducaoLeite() { return refProducaoLeite; }
    public void setRefProducaoLeite(Double refProducaoLeite) { this.refProducaoLeite = refProducaoLeite; }

    public Double getRazaoProducaoLeite() { return razaoProducaoLeite; }
    public void setRazaoProducaoLeite(Double razaoProducaoLeite) { this.razaoProducaoLeite = razaoProducaoLeite; }

    public Double getCalcRacao() { return calcRacao; }
    public void setCalcRacao(Double calcRacao) { this.calcRacao = calcRacao; }

    public Double getRefRacao() { return refRacao; }
    public void setRefRacao(Double refRacao) { this.refRacao = refRacao; }

    public Double getRazaoRacao() { return razaoRacao; }
    public void setRazaoRacao(Double razaoRacao) { this.razaoRacao = razaoRacao; }

    public Double getCalcPerdaSolo() { return calcPerdaSolo; }
    public void setCalcPerdaSolo(Double calcPerdaSolo) { this.calcPerdaSolo = calcPerdaSolo; }

    public Double getRefPerdaSolo() { return refPerdaSolo; }
    public void setRefPerdaSolo(Double refPerdaSolo) { this.refPerdaSolo = refPerdaSolo; }

    public Double getRazaoPerdaSolo() { return razaoPerdaSolo; }
    public void setRazaoPerdaSolo(Double razaoPerdaSolo) { this.razaoPerdaSolo = razaoPerdaSolo; }

    public Double getCalcAguaUsada() { return calcAguaUsada; }
    public void setCalcAguaUsada(Double calcAguaUsada) { this.calcAguaUsada = calcAguaUsada; }

    public Double getRefAguaUsada() { return refAguaUsada; }
    public void setRefAguaUsada(Double refAguaUsada) { this.refAguaUsada = refAguaUsada; }

    public Double getRazaoAguaUsada() { return razaoAguaUsada; }
    public void setRazaoAguaUsada(Double razaoAguaUsada) { this.razaoAguaUsada = razaoAguaUsada; }

    public Double getCalcCombustivelUsado() { return calcCombustivelUsado; }
    public void setCalcCombustivelUsado(Double calcCombustivelUsado) { this.calcCombustivelUsado = calcCombustivelUsado; }

    public Double getRefCombustivelUsado() { return refCombustivelUsado; }
    public void setRefCombustivelUsado(Double refCombustivelUsado) { this.refCombustivelUsado = refCombustivelUsado; }

    public Double getRazaoCombustivelUsado() { return razaoCombustivelUsado; }
    public void setRazaoCombustivelUsado(Double razaoCombustivelUsado) { this.razaoCombustivelUsado = razaoCombustivelUsado; }

    public Double getCalcEletricidade() { return calcEletricidade; }
    public void setCalcEletricidade(Double calcEletricidade) { this.calcEletricidade = calcEletricidade; }

    public Double getRefEletricidade() { return refEletricidade; }
    public void setRefEletricidade(Double refEletricidade) { this.refEletricidade = refEletricidade; }

    public Double getRazaoEletricidade() { return razaoEletricidade; }
    public void setRazaoEletricidade(Double razaoEletricidade) { this.razaoEletricidade = razaoEletricidade; }

    public Double getCalcGado() { return calcGado; }
    public void setCalcGado(Double calcGado) { this.calcGado = calcGado; }

    public Double getRefGado() { return refGado; }
    public void setRefGado(Double refGado) { this.refGado = refGado; }

    public Double getRazaoGado() { return razaoGado; }
    public void setRazaoGado(Double razaoGado) { this.razaoGado = razaoGado; }

    public Double getCalcMaquinarios() { return calcMaquinarios; }
    public void setCalcMaquinarios(Double calcMaquinarios) { this.calcMaquinarios = calcMaquinarios; }

    public Double getRefMaquinarios() { return refMaquinarios; }
    public void setRefMaquinarios(Double refMaquinarios) { this.refMaquinarios = refMaquinarios; }

    public Double getRazaoMaquinarios() { return razaoMaquinarios; }
    public void setRazaoMaquinarios(Double razaoMaquinarios) { this.razaoMaquinarios = razaoMaquinarios; }

    public Double getCalcMaoObra() { return calcMaoObra; }
    public void setCalcMaoObra(Double calcMaoObra) { this.calcMaoObra = calcMaoObra; }

    public Double getRefMaoObra() { return refMaoObra; }
    public void setRefMaoObra(Double refMaoObra) { this.refMaoObra = refMaoObra; }

    public Double getRazaoMaoObra() { return razaoMaoObra; }
    public void setRazaoMaoObra(Double razaoMaoObra) { this.razaoMaoObra = razaoMaoObra; }

    public Double getCalcBens() { return calcBens; }
    public void setCalcBens(Double calcBens) { this.calcBens = calcBens; }

    public Double getRefBens() { return refBens; }
    public void setRefBens(Double refBens) { this.refBens = refBens; }

    public Double getRazaoBens() { return razaoBens; }
    public void setRazaoBens(Double razaoBens) { this.razaoBens = razaoBens; }
}
