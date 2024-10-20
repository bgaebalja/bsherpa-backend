package bgaebalja.bsherpa.util;

import bgaebalja.bsherpa.question.domain.Difficulty;
import bgaebalja.bsherpa.question.domain.QuestionType;
import bgaebalja.bsherpa.question.exception.DifficultyNotFoundException;
import bgaebalja.bsherpa.question.exception.QuestionTypeNotFoundException;

import static bgaebalja.bsherpa.question.exception.ExceptionMessage.DIFFICULTY_NOT_FOUND_EXCEPTION_MESSAGE;
import static bgaebalja.bsherpa.question.exception.ExceptionMessage.QUESTION_TYPE_NOT_FOUND_EXCEPTION_MESSAGE;

public class QuestionClassifier {
    private static final String CHOICE_FROM_TWO_CODE = "20";
    private static final String CHOICE_FROM_TWO_NAME = "2지 선택";
    private static final String CHOICE_FROM_FIVE_CODE = "50";
    private static final String CHOICE_FROM_FIVE_NAME = "5지 선택";

    // TODO: 단답 무순형, 단답 그룹형 코드 추가
    private static final String SUBJECTIVE_CODE = "60";
    private static final String SUBJECTIVE_KEYWORD = "단답";
    private static final String DESCRIPTIVE_CODE = "85";
    private static final String DESCRIPTIVE_NAME = "서술형";

    private static final String COMMENTARY = "해설 참조";

    private static final String VERY_EASY_CODE = "01";
    private static final String VERY_EASY_NAME = "최하";
    private static final String EASY_CODE = "02";
    private static final String EASY_NAME = "하";
    private static final String NORMAL_CODE = "03";
    private static final String NORMAL_NAME = "중";
    private static final String HARD_CODE = "04";
    private static final String HARD_NAME = "상";
    private static final String VERY_HARD_CODE = "05";
    private static final String VERY_HARD_NAME = "최상";

    public static QuestionType classifyType(String type) {
        String target = type.trim();

        if (target.equals(CHOICE_FROM_TWO_CODE) || target.equals(CHOICE_FROM_TWO_NAME)) {
            return QuestionType.CHOICE_FROM_TWO;
        }
        if (target.equals(CHOICE_FROM_FIVE_CODE) || target.equals(CHOICE_FROM_FIVE_NAME)) {
            return QuestionType.CHOICE_FROM_FIVE;
        }
        if (target.equals(SUBJECTIVE_CODE) || target.startsWith(SUBJECTIVE_KEYWORD)) {
            return QuestionType.SUBJECTIVE;
        }
        if (target.equals(DESCRIPTIVE_CODE) || target.equals(DESCRIPTIVE_NAME)) {
            return QuestionType.DESCRIPTIVE;
        }

        throw new QuestionTypeNotFoundException(String.format(QUESTION_TYPE_NOT_FOUND_EXCEPTION_MESSAGE, target));
    }

    // 답만 주어진 경우 "해설 참조"를 통해 서술형임을 구분 => 정답 채점 제외
    public static boolean isDescriptive(String value) {
        String target = value.trim();

        return target.equals(DESCRIPTIVE_CODE) || target.equals(DESCRIPTIVE_NAME) || target.equals(COMMENTARY);
    }

    public static Difficulty classifyDifficulty(String difficulty) {
        String target = difficulty.trim();

        if (target.equals(VERY_EASY_CODE) || target.equals(VERY_EASY_NAME)) {
            return Difficulty.VERY_EASY;
        }
        if (target.equals(EASY_CODE) || target.equals(EASY_NAME)) {
            return Difficulty.EASY;
        }
        if (target.equals(NORMAL_CODE) || target.equals(NORMAL_NAME)) {
            return Difficulty.NORMAL;
        }
        if (target.equals(HARD_CODE) || target.equals(HARD_NAME)) {
            return Difficulty.HARD;
        }
        if (target.equals(VERY_HARD_CODE) || target.equals(VERY_HARD_NAME)) {
            return Difficulty.VERY_HARD;
        }

        throw new DifficultyNotFoundException(String.format(DIFFICULTY_NOT_FOUND_EXCEPTION_MESSAGE, target));
    }
}
