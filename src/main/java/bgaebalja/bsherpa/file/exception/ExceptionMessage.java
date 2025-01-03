package bgaebalja.bsherpa.file.exception;

public class ExceptionMessage {
    public static final String S3_UPLOAD_FAILED_EXCEPTION_MESSAGE = "S3 업로드 과정에서 오류가 발생했습니다.";
    public static final String FILE_NOT_FOUND_EXCEPTION_MESSAGE
            = "해당하는 파일을 찾을 수 없습니다. 다시 시도해 주세요. 전송된 ID: %d";
    public static final String TARGET_FILE_NOT_FOUND_EXCEPTION_MESSAGE
            = "해당하는 파일을 찾을 수 없습니다. 다시 시도해 주세요. 전송된 대상 타입: %s, 전송된 대상 ID: %d";
}
