package br.com.emergia.repository;

import br.com.emergia.database.Sustentabilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface SustentabilidadeRepository extends JpaRepository<Sustentabilidade, Long> {

    @Query(
      value = "SELECT * FROM sustentabilidade ORDER BY id DESC LIMIT 1",
      nativeQuery = true
    )
    Optional<Sustentabilidade> findLatest();
}
