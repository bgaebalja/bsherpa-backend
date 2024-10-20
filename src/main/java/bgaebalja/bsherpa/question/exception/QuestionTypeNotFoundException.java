package bgaebalja.bsherpa.question.exception;

import javax.persistence.EntityNotFoundException;

public class QuestionTypeNotFoundException extends EntityNotFoundException {
    public QuestionTypeNotFoundException(String message) {
        super(message);
    }
}
