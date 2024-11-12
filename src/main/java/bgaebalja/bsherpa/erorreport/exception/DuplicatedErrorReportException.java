package bgaebalja.bsherpa.erorreport.exception;

import javax.persistence.EntityExistsException;

public class DuplicatedErrorReportException extends EntityExistsException {
    public DuplicatedErrorReportException(String message) {
        super(message);
    }
}
