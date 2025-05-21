package br.com.emergia.services;

import br.com.emergia.database.Sustentabilidade;
import br.com.emergia.repository.RelatorioRepository;
import br.com.emergia.repository.SustentabilidadeRepository;
import org.springframework.stereotype.Service;

@Service
public class SustentabilidadeService {

    private final SustentabilidadeRepository repo;
    private final RelatorioRepository relRepo; // injete também seu RelatorioRepository
    private final CalculoTotal calculoTotal;

    public SustentabilidadeService(SustentabilidadeRepository repo,
                                   RelatorioRepository relRepo,
                                   CalculoTotal calculoTotal) {
        this.repo = repo;
        this.relRepo = relRepo;
        this.calculoTotal = calculoTotal;
    }

    public Sustentabilidade calcularESalvar() {
        // Exemplo de fluxo: busca último relatório, calcula índices...
        var rel = relRepo.findLatest()
            .orElseThrow(() -> new RuntimeException("Nenhum relatório para processar"));

        Sustentabilidade s = new Sustentabilidade();
        s.setRelatorio(rel);

        // preencha eyr, elr, esi, eir usando calculoTotal ou sua lógica
        calculoTotal.calcularTotais();
        s.setEyr(calculoTotal.getEyr());
        s.setElr(calculoTotal.getElr());
        s.setEsi(calculoTotal.getEsi());
        s.setEir(calculoTotal.getEir());
        return repo.save(s);
    }

    public Sustentabilidade buscarUltima() {
        return repo.findLatest()
                   .orElseThrow(() -> new RuntimeException("Nenhum índice de sustentabilidade encontrado"));
    }
}
