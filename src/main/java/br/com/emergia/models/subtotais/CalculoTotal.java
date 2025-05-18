package br.com.emergia.models.subtotais;

import br.com.emergia.database.Relatorio;
import br.com.emergia.repository.RelatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculoTotal {

    private double totalRenovavel;
    private double totalNaoRenovavel;
    private double totalAmbiental;

    private double totalBens;
    private double totalOperacoesProducao;
    private double totalContribuicaoHumana;

    private double totalProducao;

    private double calculoTotal;

    @Autowired
    private RelatorioRepository relatorioRepository;

    public double getCalculoTotal() {
        return calculoTotal;
    }

    public void calcularTotais() {
        // Recupera o último relatório do banco de dados
        Relatorio ultimo = relatorioRepository.findLatest()
                .orElseThrow(() -> new RuntimeException("Nenhum relatório encontrado"));

        // Usando as classes de subtotal com os valores "ref" do banco
        SubtotalAmbiental subtotalAmbiental = new SubtotalAmbiental();
        totalRenovavel = subtotalAmbiental.calcRenovavel(ultimo.getRefPotencialQuimico());
        totalNaoRenovavel = subtotalAmbiental.calcNaoRenovavel(ultimo.getRefAguaUsada(), ultimo.getRefPerdaSolo());
        totalAmbiental = subtotalAmbiental.somaAmbiental();

        SubtotalContribuicaoHumana subtotalContribuicaoHumana = new SubtotalContribuicaoHumana();
        totalBens = subtotalContribuicaoHumana.calcBens(ultimo.getRefBens());
        totalOperacoesProducao = subtotalContribuicaoHumana.calcOperacoesProducao(
                ultimo.getRefCombustivelUsado(), ultimo.getRefEletricidade(), ultimo.getRefGado(),
                ultimo.getRefMaoObra(), ultimo.getRefMaquinarios(), ultimo.getRefRacao(), ultimo.getRefCuidadoSolo());
        totalContribuicaoHumana = subtotalContribuicaoHumana.calcContribuicaoHumana(
                ultimo.getRefBens(), ultimo.getRefCombustivelUsado(), ultimo.getRefEletricidade(), ultimo.getRefGado(),
                ultimo.getRefMaoObra(), ultimo.getRefMaquinarios(), ultimo.getRefRacao(), ultimo.getRefCuidadoSolo());

        SubtotalPoducao subtotalPoducao = new SubtotalPoducao();
        totalProducao = subtotalPoducao.calcProducaoLeite(ultimo.getRefProducaoLeite());

        calculoTotal = totalAmbiental + totalContribuicaoHumana + totalProducao;
    }

    // Getters para as porcentagens (já implementados para evitar divisão por zero)
    public double getPorcentagemRenovavel() {
        return (calculoTotal != 0) ? (totalRenovavel / calculoTotal) * 100 : 0;
    }

    public double getPorcentagemNaoRenovavel() {
        return (calculoTotal != 0) ? (totalNaoRenovavel / calculoTotal) * 100 : 0;
    }

    public double getPorcentagemAmbiental() {
        return (calculoTotal != 0) ? (totalAmbiental / calculoTotal) * 100 : 0;
    }

    public double getPorcentagemBens() {
        return (calculoTotal != 0) ? (totalBens / calculoTotal) * 100 : 0;
    }

    public double getPorcentagemOperacoesProducao() {
        return (calculoTotal != 0) ? (totalOperacoesProducao / calculoTotal) * 100 : 0;
    }

    public double getPorcentagemContribuicaoHumana() {
        return (calculoTotal != 0) ? (totalContribuicaoHumana / calculoTotal) * 100 : 0;
    }

    public double getPorcentagemProducao() {
        return (calculoTotal != 0) ? (totalProducao / calculoTotal) * 100 : 0;
    }

    public double getTotalRenovavel() {
        return totalRenovavel;
    }

    public double getTotalNaoRenovavel() {
        return totalNaoRenovavel;
    }

    public double getTotalAmbiental() {
        return totalAmbiental;
    }

    public double getTotalBens() {
        return totalBens;
    }

    public double getTotalOperacoesProducao() {
        return totalOperacoesProducao;
    }

    public double getTotalContribuicaoHumana() {
        return totalContribuicaoHumana;
    }

    public double getTotalProducao() {
        return totalProducao;
    }
}
