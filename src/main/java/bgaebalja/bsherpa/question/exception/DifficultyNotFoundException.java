package bgaebalja.bsherpa.question.exception;

import javax.persistence.EntityNotFoundException;

public class DifficultyNotFoundException extends EntityNotFoundException {
    public DifficultyNotFoundException(String message) {
        super(message);
    }
}
