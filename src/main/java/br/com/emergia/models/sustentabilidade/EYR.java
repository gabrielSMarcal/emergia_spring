package br.com.emergia.models.sustentabilidade;


import br.com.emergia.models.subtotais.SubtotalAmbiental;
import br.com.emergia.database.Relatorio;
import br.com.emergia.models.subtotais.SubtotalContribuicaoHumana;
import br.com.emergia.repository.RelatorioRepository;

public class EYR {
    private final RelatorioRepository repo;

    public EYR(RelatorioRepository repo) {
        this.repo = repo;
    }

    public double calEYR(){
        Relatorio ultimo = repo.findLatest()
                .orElseThrow(() -> new RuntimeException("Nenhum relatório encontrado"));
        SubtotalAmbiental subtotalAmbiental = new SubtotalAmbiental();

        SubtotalContribuicaoHumana subtotalContribuicaoHumana = new SubtotalContribuicaoHumana();

        // EYR = (R + N + F)/ F
        // Mede o quanto de emergia total é gerada a partir da emergia importada (F)
        double resulEYR = (subtotalAmbiental.calcRenovavel(ultimo.getRefPotencialQuimico()) + subtotalAmbiental.calcNaoRenovavel(ultimo.getRefAguaUsada(), ultimo.getRefPerdaSolo()) + subtotalContribuicaoHumana.calcContribuicaoHumana(
                ultimo.getRefBens(), ultimo.getRefCombustivelUsado(), ultimo.getRefEletricidade(), ultimo.getRefGado(),
                ultimo.getRefMaoObra(), ultimo.getRefMaquinarios(), ultimo.getRefRacao(), ultimo.getRefCuidadoSolo())) / subtotalContribuicaoHumana.calcContribuicaoHumana(
                ultimo.getRefBens(), ultimo.getRefCombustivelUsado(), ultimo.getRefEletricidade(), ultimo.getRefGado(),
                ultimo.getRefMaoObra(), ultimo.getRefMaquinarios(), ultimo.getRefRacao(), ultimo.getRefCuidadoSolo());

        return resulEYR;

        /*
        EYR > 5	Muito alto – Sistema altamente eficiente e produtivo. Traz mais emergia do ambiente do que consome da economia.

        EYR entre 2 e 5	Moderado – Sistema ainda é bom, traz mais do que consome, mas com menor margem.

        EYR ≈ 1	Baixo – Sistema consome quase o mesmo tanto que produz. Pouco retorno.

        EYR < 1	Muito baixo / Ineficiente – Consome mais emergia da economia do que o que gera como retorno. Sistema insustentável.

        */
    }

}
