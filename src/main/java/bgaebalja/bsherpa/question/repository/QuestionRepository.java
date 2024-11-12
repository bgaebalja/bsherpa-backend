package bgaebalja.bsherpa.question.repository;

import bgaebalja.bsherpa.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByItemId(Long itemId);
}
