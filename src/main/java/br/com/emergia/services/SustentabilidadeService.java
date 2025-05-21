package br.com.emergia.services;

import br.com.emergia.database.Sustentabilidade;
import br.com.emergia.repository.*;
import br.com.emergia.models.sustentabilidade.*;
import org.springframework.stereotype.Service;

@Service
public class SustentabilidadeService  {

    private final RelatorioRepository relRepo;
    private final SustentabilidadeRepository susRepo;

    public SustentabilidadeService(RelatorioRepository relRepo,
                                   SustentabilidadeRepository susRepo) {
        this.relRepo = relRepo;
        this.susRepo = susRepo;
    }

    public Sustentabilidade calcularESalvar() {
        var rel = relRepo.findLatest()
            .orElseThrow(() -> new RuntimeException("Nenhum relatório"));

        double eyr = new EYR(relRepo).calEYR();
        double elr = new ELR(relRepo).calELR();
        double esi = new ESI(relRepo).calESI();
        double eir = new EIR(relRepo).calEIR();

        Sustentabilidade s = new Sustentabilidade();
        s.setRelatorio(rel);
        s.setEyr(eyr);
        s.setElr(elr);
        s.setEsi(esi);
        s.setEir(eir);
        return susRepo.save(s);
    }

    public Sustentabilidade buscarUltima() {
        return susRepo.findFirstByOrderByIdDesc()
            .orElseThrow(() -> new RuntimeException("Nenhuma sustentabilidade registrada"));
    }
}
