package br.com.emergia.models.sustentabilidade;


import br.com.emergia.database.Relatorio;
import br.com.emergia.repository.RelatorioRepository;

public class ESI {
    private final RelatorioRepository repo;
    public ESI(RelatorioRepository repo) { this.repo = repo; }

    public double calESI(){
        double eyr = new EYR(repo).calEYR();
        double elr = new ELR(repo).calELR();
        return eyr / elr;

        /*
        ESI > 10: Sistema altamente sustentável.

        ESI ≈ 1-10: Sustentabilidade moderada.

        ESI < 1: Sistema com baixa sustentabilidade, alto impacto ambiental.

        */
    }

}

