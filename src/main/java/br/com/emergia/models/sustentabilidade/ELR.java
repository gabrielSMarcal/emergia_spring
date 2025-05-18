package br.com.emergia.models.sustentabilidade;


import br.com.emergia.models.subtotais.SubtotalAmbiental;
import br.com.emergia.database.Relatorio;
import br.com.emergia.models.subtotais.SubtotalContribuicaoHumana;
import br.com.emergia.repository.RelatorioRepository;

public class ELR {
    private RelatorioRepository relatorioRepository;


    public double calELR(){
        Relatorio ultimo = relatorioRepository.findLatest()
                .orElseThrow(() -> new RuntimeException("Nenhum relatório encontrado"));

        SubtotalAmbiental subtotalAmbiental = new SubtotalAmbiental();

        SubtotalContribuicaoHumana subtotalContribuicaoHumana = new SubtotalContribuicaoHumana();

        // ELR = N + F / R
        // Mede quanto o sistema depende de recursos não renováveis e de contribuições humanas em relação aos recursos renováveis da natureza.
        double resulELR = (subtotalAmbiental.calcNaoRenovavel(ultimo.getRefAguaUsada(), ultimo.getRefPerdaSolo()) + subtotalContribuicaoHumana.calcContribuicaoHumana(
                ultimo.getRefBens(), ultimo.getRefCombustivelUsado(), ultimo.getRefEletricidade(), ultimo.getRefGado(),
                ultimo.getRefMaoObra(), ultimo.getRefMaquinarios(), ultimo.getRefRacao(), ultimo.getRefCuidadoSolo())) / subtotalAmbiental.calcRenovavel(ultimo.getRefPotencialQuimico());

        return resulELR;

        /*
        ELR < 1	Uso predominantemente renovável → baixa carga ambiental

        ELR ≈ 1	Uso equilibrado entre renováveis e não renováveis

        ELR > 1	Uso mais intenso de recursos não renováveis → alta carga ambiental

        ELR muito alto	Pressão ambiental crítica e insustentável

        */
    }

}
