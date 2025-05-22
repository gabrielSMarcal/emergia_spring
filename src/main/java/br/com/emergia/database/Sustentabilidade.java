package br.com.emergia.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sustentabilidade")
public class Sustentabilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "relatorio_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Relatorio relatorio;

    private Double eyr;
    private Double elr;
    private Double esi;
    private Double eir;

    private LocalDateTime criadoEm = LocalDateTime.now();

    // getters/setters
    public Long getId() { return id; }
    public Relatorio getRelatorio() { return relatorio; }
    public void setRelatorio(Relatorio relatorio) { this.relatorio = relatorio; }
    public Double getEyr() { return eyr; }
    public void setEyr(Double eyr) { this.eyr = eyr; }
    public Double getElr() { return elr; }
    public void setElr(Double elr) { this.elr = elr; }
    public Double getEsi() { return esi; }
    public void setEsi(Double esi) { this.esi = esi; }
    public Double getEir() { return eir; }
    public void setEir(Double eir) { this.eir = eir; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
}
