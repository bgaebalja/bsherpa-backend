package bgaebalja.bsherpa.file.exception;

public class S3UploadFailedException extends RuntimeException {
    public S3UploadFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
