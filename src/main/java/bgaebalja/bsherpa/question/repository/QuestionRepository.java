package bgaebalja.bsherpa.question.repository;

import bgaebalja.bsherpa.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByItemId(Long itemId);
}
