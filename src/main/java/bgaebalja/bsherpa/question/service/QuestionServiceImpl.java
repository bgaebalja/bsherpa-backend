package bgaebalja.bsherpa.question.service;

import bgaebalja.bsherpa.question.domain.CreateQuestionRequest;
import bgaebalja.bsherpa.question.domain.Question;
import bgaebalja.bsherpa.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Override
    @Transactional(isolation = READ_COMMITTED, timeout = 20)
    public Long createQuestion(CreateQuestionRequest createQuestionRequest) {
        // TODO: Collection 엔티티 연결
        return questionRepository.save(Question.from(createQuestionRequest, null)).getId();
    }
}
