package bgaebalja.bsherpa.passage.repository;


import bgaebalja.bsherpa.passage.domain.Passage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassageRepository extends JpaRepository<Passage, Long> {
}
