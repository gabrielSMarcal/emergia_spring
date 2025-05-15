package br.com.emergia.repository;

import br.com.emergia.database.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {

    @Query("SELECT r FROM Relatorio r ORDER BY r.relatId DESC")
    Optional<Relatorio> buscarUltimoRelatorio();
}
