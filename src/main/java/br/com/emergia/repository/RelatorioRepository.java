package br.com.emergia.repository;

import br.com.emergia.database.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {
    @Query(value = "SELECT * FROM relatorio ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<Relatorio> findLatest();
}
