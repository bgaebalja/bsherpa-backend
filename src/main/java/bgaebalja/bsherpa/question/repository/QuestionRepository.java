package bgaebalja.bsherpa.question.repository;

import bgaebalja.bsherpa.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
