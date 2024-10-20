package bgaebalja.bsherpa.question.service;

import bgaebalja.bsherpa.question.domain.CreateQuestionRequest;

public interface QuestionService {
    Long createQuestion(CreateQuestionRequest createQuestionRequest);
}
