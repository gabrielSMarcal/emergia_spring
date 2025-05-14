package br.com.emergia.repository;

import br.com.emergia.database.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {

    @Query(value = """
        SELECT 
            r.relat_id,
            r.calc_agua_usada, r.calc_bens, r.calc_combustivel_usado, r.calc_cuidado_solo, r.calc_eletricidade, r.calc_gado, r.calc_mao_obra, r.calc_maquinarios, r.calc_perda_solo, r.calc_potencial_quimico, r.calc_producao_leite, r.calc_racao,
            r.ref_agua_usada, r.ref_bens, r.ref_combustivel_usado, r.ref_cuidado_solo, r.ref_eletricidade, r.ref_gado, r.ref_mao_obra, r.ref_maquinarios, r.ref_perda_solo, r.ref_potencial_quimico, r.ref_producao_leite, r.ref_racao,
            r.razao_agua_usada, r.razao_bens, r.razao_combustivel_usado, r.razao_cuidado_solo, r.razao_eletricidade, r.razao_gado, r.razao_mao_obra, r.razao_maquinarios, r.razao_perda_solo, r.razao_potencial_quimico, r.razao_producao_leite, r.razao_racao
        FROM relatorio r
        ORDER BY r.relat_id DESC
        LIMIT 1
        """, nativeQuery = true)
    Relatorio buscarUltimoRelatorio();
}
