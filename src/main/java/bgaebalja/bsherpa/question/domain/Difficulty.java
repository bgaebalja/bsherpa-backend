package bgaebalja.bsherpa.question.domain;

import bgaebalja.bsherpa.question.exception.DifficultyNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static bgaebalja.bsherpa.question.exception.ExceptionMessage.DIFFICULTY_NOT_FOUND_EXCEPTION_MESSAGE;

@RequiredArgsConstructor
@Getter
public enum Difficulty {
    VERY_EASY("01", "최하"),
    EASY("02", "하"),
    NORMAL("03", "중"),
    HARD("04", "상"),
    VERY_HARD("05", "최상");

    private final String code;
    private final String name;

    public static String getNameByCode(String code) {
        for (Difficulty difficulty : Difficulty.values()) {
            if (difficulty.getCode().equals(code)) {
                return difficulty.getName();
            }
        }

        throw new DifficultyNotFoundException(String.format(DIFFICULTY_NOT_FOUND_EXCEPTION_MESSAGE, code));
    }
}
