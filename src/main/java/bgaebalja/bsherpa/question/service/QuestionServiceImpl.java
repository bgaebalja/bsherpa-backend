package bgaebalja.bsherpa.question.service;

import bgaebalja.bsherpa.question.domain.CreateQuestionRequest;
import bgaebalja.bsherpa.question.domain.Question;
import bgaebalja.bsherpa.question.repository.QuestionRepository;
import bgaebalja.bsherpa.util.FormatValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static bgaebalja.bsherpa.util.LogConstant.QUESTION_NOT_SAVED_MESSAGE;
import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private static final Logger log = LoggerFactory.getLogger(QuestionService.class);

    private final QuestionRepository questionRepository;

    @Override
    @Transactional(isolation = READ_COMMITTED, timeout = 20)
    public Long createQuestion(CreateQuestionRequest createQuestionRequest) {
        return questionRepository.save(Question.from(createQuestionRequest, null)).getId();
    }

    @Override
    @Transactional(isolation = READ_COMMITTED, timeout = 10)
    public void addErrorReportCount(Long itemId) {
        List<Question> questions = getQuestions(itemId);
        if (FormatValidator.hasValue(questions)) {
            questions.stream().forEach(Question::addErrorReport);
            questionRepository.saveAll(questions);

            return;
        }

        log.info(QUESTION_NOT_SAVED_MESSAGE);
    }

    private List<Question> getQuestions(Long itemId) {
        return questionRepository.findByItemId(itemId);
    }
}
