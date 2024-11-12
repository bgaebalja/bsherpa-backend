package bgaebalja.bsherpa.file.exception;

import javax.persistence.EntityNotFoundException;

public class FileNotFoundException extends EntityNotFoundException {
    public FileNotFoundException(String message) {
        super(message);
    }
}
