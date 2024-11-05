package bgaebalja.bsherpa.exam.exception;

import javax.persistence.EntityNotFoundException;

public class ExamNotFoundException extends EntityNotFoundException {
    public ExamNotFoundException(String message) {
        super(message);
    }
}
