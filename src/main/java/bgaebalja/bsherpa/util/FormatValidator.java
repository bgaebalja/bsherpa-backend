package bgaebalja.bsherpa.util;

import bgaebalja.bsherpa.exception.EmailNoValueException;
import bgaebalja.bsherpa.exception.IdNoValueException;
import bgaebalja.bsherpa.exception.InvalidEmailException;
import bgaebalja.bsherpa.exception.InvalidIdException;

import java.util.List;

import static bgaebalja.bsherpa.exception.ExceptionMessage.*;
import static bgaebalja.bsherpa.util.RegularExpressionConstant.EMAIL_PATTERN;
import static bgaebalja.bsherpa.util.RegularExpressionConstant.POSITIVE_OR_ZERO_INTEGER_PATTERN;

public class FormatValidator {
    public static void validateEmail(String email) {
        if (!hasValue(email)) {
            throw new EmailNoValueException(EMAIL_NO_VALUE_EXCEPTION_MESSAGE);
        }
        if (!isValid(email, EMAIL_PATTERN)) {
            throw new InvalidEmailException(String.format(INVALID_EMAIL_EXCEPTION_MESSAGE + email));
        }
    }

    public static void validatePositiveOrZeroInteger(String positiveOrZeroInteger) {
        if (!hasValue(positiveOrZeroInteger)) {
            throw new IdNoValueException(ID_NO_VALUE_EXCEPTION_MESSAGE);
        }
        if (!isValid(positiveOrZeroInteger, POSITIVE_OR_ZERO_INTEGER_PATTERN)) {
            throw new InvalidIdException(String.format(INVALID_ID_EXCEPTION_MESSAGE, positiveOrZeroInteger));
        }
    }

    public static boolean hasValue(String value) {
        return value != null && !value.isBlank();
    }

    public static boolean hasValue(Object value) {
        return value != null;
    }

    public static boolean hasValue(List value) {
        return value != null && !value.isEmpty();
    }

    private static boolean isValid(String value, String pattern) {
        return value.matches(pattern);
    }
}
