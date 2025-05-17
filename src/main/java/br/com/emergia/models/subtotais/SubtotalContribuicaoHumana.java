package br.com.emergia.models.subtotais;

import br.com.emergia.models.contribuicaoHumana.bens.ValorConsumoManutencao;
import br.com.emergia.models.contribuicaoHumana.operacoesProducao.*;

public class SubtotalContribuicaoHumana {

    private double totalBens;
    private double totalOperacoesProducao;
    private double totalContribuicaoHumana;

    public double getTotalBens() {
        return totalBens;
    }

    public double getTotalOperacoesProducao() {
        return totalOperacoesProducao;
    }

    public double getTotalContribuicaoHumana() {
        return totalContribuicaoHumana;
    }

    public double calcBens () {

        // pega o valor ref de ValorConsumoManutencao
        ValorConsumoManutencao vcm = new ValorConsumoManutencao();

        totalBens = vcm.getResulRefEmergiaSolarVCM();

        return totalBens;
    }

    public double calcOperacoesProducao () {

        // pega o valor ref de operacoesProducao
        CombustivelUsado comU = new CombustivelUsado();
        Eletricidade     ele  = new Eletricidade();
        Gado             gado = new Gado();
        MaoObra          mo   = new MaoObra();
        Maquinarios      maq  = new Maquinarios();
        Racao            rac  = new Racao();
        CuidadoSolo      cs   = new CuidadoSolo();

        // totalOperacoesProducao = soma de todas as refs
        totalOperacoesProducao = comU.getResulRefEmergiaSolarCombustivelUsado() +
                ele.getResulRefEmergiaSolarEletricidade() +
                gado.getResulRefEmergiaSolarGado() +
                mo.getResulRefEmergiaSolarMaoObra() +
                maq.getResulRefEmergiaSolarMaquinario() +
                rac.getResulRefEmergiaSolarRacao() +
                cs.getResulRefEmergiaSolarCuidadoSolo();

        return totalOperacoesProducao;
    }

    public double calcContribuicaoHumana () {

        totalContribuicaoHumana = calcBens() + calcOperacoesProducao();

        return totalContribuicaoHumana;
    }
}
