package bgaebalja.bsherpa.question.exception;

import javax.persistence.EntityNotFoundException;

public class QuestionNotFoundException extends EntityNotFoundException {
    public QuestionNotFoundException(String message) {
        super(message);
    }
}
