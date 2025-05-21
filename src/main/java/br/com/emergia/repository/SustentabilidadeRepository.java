package br.com.emergia.repository;

import br.com.emergia.database.Sustentabilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SustentabilidadeRepository extends JpaRepository<Sustentabilidade, Long> {
    Optional<Sustentabilidade> findFirstByOrderByIdDesc();
}
