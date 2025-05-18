package br.com.emergia.models.sustentabilidade;


import br.com.emergia.database.Relatorio;
import br.com.emergia.repository.RelatorioRepository;

public class ESI {
    private RelatorioRepository relatorioRepository;



    public double calESI(){
        Relatorio ultimo = relatorioRepository.findLatest()
                .orElseThrow(() -> new RuntimeException("Nenhum relatório encontrado"));

        EYR eyr = new EYR();
        ELR elr = new ELR();
        // ESI = EYR / ELR
        // indicador que avalia o potencial de sustentabilidade de um sistema, relacionando o benefício ambiental com a carga ambiental que ele gera.
        double resulESI = eyr.calEYR() / elr.calELR();

        return resulESI;

        /*
        ESI > 10: Sistema altamente sustentável.

        ESI ≈ 1-10: Sustentabilidade moderada.

        ESI < 1: Sistema com baixa sustentabilidade, alto impacto ambiental.

        */
    }

}

