package bgaebalja.bsherpa.util;

import bgaebalja.bsherpa.exception.IdNoValueException;
import bgaebalja.bsherpa.exception.InvalidIdException;

import java.util.List;

import static bgaebalja.bsherpa.exception.ExceptionMessage.ID_NO_VALUE_EXCEPTION_MESSAGE;
import static bgaebalja.bsherpa.exception.ExceptionMessage.INVALID_ID_EXCEPTION_MESSAGE;
import static bgaebalja.bsherpa.util.RegularExpressionConstant.POSITIVE_INTEGER_PATTERN;

public class FormatValidator {
    public static void validatePositiveInteger(String positiveInteger) {
        if (!hasValue(positiveInteger)) {
            throw new IdNoValueException(ID_NO_VALUE_EXCEPTION_MESSAGE);
        }
        if (!isValid(positiveInteger, POSITIVE_INTEGER_PATTERN)) {
            throw new InvalidIdException(String.format(INVALID_ID_EXCEPTION_MESSAGE, positiveInteger));
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
