package br.com.emergia.services;

import br.com.emergia.database.Relatorio;
import br.com.emergia.database.Sustentabilidade;
import br.com.emergia.models.sustentabilidade.EIR;
import br.com.emergia.models.sustentabilidade.ELR;
import br.com.emergia.models.sustentabilidade.ESI;
import br.com.emergia.models.sustentabilidade.EYR;
import br.com.emergia.repository.RelatorioRepository;
import br.com.emergia.repository.SustentabilidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SustentabilidadeService {
    
    private final SustentabilidadeRepository sustentabilidadeRepository;
    private final RelatorioRepository relatorioRepository;
    
    @Autowired
    public SustentabilidadeService(SustentabilidadeRepository sustentabilidadeRepository, RelatorioRepository relatorioRepository) {
        this.sustentabilidadeRepository = sustentabilidadeRepository;
        this.relatorioRepository = relatorioRepository;
    }
    
    public Sustentabilidade calcularEArmazenarIndices(Long relatorioId) {
        Relatorio relatorio = relatorioRepository.findById(relatorioId)
                .orElseThrow(() -> new RuntimeException("Relatório não encontrado com ID: " + relatorioId));
        
        // Calcular os índices de sustentabilidade
        EYR eyr = new EYR(relatorioRepository);
        ELR elr = new ELR(relatorioRepository);
        ESI esi = new ESI(relatorioRepository);
        EIR eir = new EIR(relatorioRepository);
        
        double eyrValue = eyr.calEYR();
        double elrValue = elr.calELR();
        double esiValue = esi.calESI();
        double eirValue = eir.calEIR();
        
        // Criar e salvar a entidade de sustentabilidade
        Sustentabilidade sustentabilidade = new Sustentabilidade();
        sustentabilidade.setRelatorio(relatorio);
        sustentabilidade.setEyr(eyrValue);
        sustentabilidade.setElr(elrValue);
        sustentabilidade.setEsi(esiValue);
        sustentabilidade.setEir(eirValue);
        sustentabilidade.setNomeDaFazenda(relatorio.getNomeDaFazenda());
        
        return sustentabilidadeRepository.save(sustentabilidade);
    }
    
    public Optional<Sustentabilidade> getLatestSustentabilidade() {
        return sustentabilidadeRepository.findLatest();
    }
    
    public List<Sustentabilidade> getAllSustentabilidade() {
        return sustentabilidadeRepository.findAll();
    }
}
