package bgaebalja.bsherpa.exception;

public abstract class InvalidValueException extends IllegalArgumentException {
    public InvalidValueException(String message) {
        super(message);
    }
}
