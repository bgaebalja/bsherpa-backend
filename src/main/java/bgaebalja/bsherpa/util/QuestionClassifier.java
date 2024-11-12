package bgaebalja.bsherpa.util;

import bgaebalja.bsherpa.question.domain.Difficulty;
import bgaebalja.bsherpa.question.domain.QuestionType;
import bgaebalja.bsherpa.question.exception.DifficultyNotFoundException;
import bgaebalja.bsherpa.question.exception.QuestionTypeNotFoundException;

import static bgaebalja.bsherpa.question.exception.ExceptionMessage.DIFFICULTY_NOT_FOUND_EXCEPTION_MESSAGE;
import static bgaebalja.bsherpa.question.exception.ExceptionMessage.QUESTION_TYPE_NOT_FOUND_EXCEPTION_MESSAGE;

public class QuestionClassifier {
    private static final String FREE_CHOICE_CODE = "10";
    private static final String FREE_CHOICE_NAME = "자유선지형";
    private static final String CHOICE_FROM_TWO_CODE = "20";
    private static final String CHOICE_FROM_TWO_NAME = "2지 선택";
    private static final String CHOICE_FROM_THREE_CODE = "30";
    private static final String CHOICE_FROM_THREE_NAME = "3지 선택";
    private static final String CHOICE_FROM_FOUR_CODE = "40";
    private static final String CHOICE_FROM_FOUR_NAME = "4지 선택";
    private static final String CHOICE_FROM_FIVE_CODE = "50";
    private static final String CHOICE_FROM_FIVE_NAME = "5지 선택";

    private static final String ORDERED_SUBJECTIVE_CODE = "60";
    private static final String ORDERED_SUBJECTIVE_NAME = "단답 유순형";
    private static final String UNORDERED_SUBJECTIVE_CODE = "61";
    private static final String UNORDERED_SUBJECTIVE_NAME = "단답 무순형";
    private static final String GROUPED_SUBJECTIVE_CODE = "63";
    private static final String GROUPED_SUBJECTIVE_NAME = "단답 그룹형";
    private static final String ESSAY_CODE = "70";
    private static final String ESSAY_NAME = "논술형";
    private static final String FILLING_SELECTION_CODE = "84";
    private static final String FILLING_SELECTION_NAME = "선택채움형";
    private static final String DESCRIPTIVE_CODE = "85";
    private static final String DESCRIPTIVE_NAME = "서술형";
    private static final String DRAG_DROP_CODE = "86";
    private static final String DRAG_DROP_NAME = "드래그 드랍";
    private static final String AREA_SELECTION_CODE = "87";
    private static final String AREA_SELECTION_NAME = "영역 선택형";
    private static final String CONNECTING_LINE_CODE = "88";
    private static final String CONNECTING_LINE_NAME = "선잇기형";
    private static final String ETC_CODE = "99";
    private static final String ETC_NAME = "기타";

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

        if (target.equals(FREE_CHOICE_CODE) || target.equals(FREE_CHOICE_NAME)) {
            return QuestionType.FREE_CHOICE;
        }
        if (target.equals(CHOICE_FROM_TWO_CODE) || target.equals(CHOICE_FROM_TWO_NAME)) {
            return QuestionType.CHOICE_FROM_TWO;
        }
        if (target.equals(CHOICE_FROM_THREE_CODE) || target.equals(CHOICE_FROM_THREE_NAME)) {
            return QuestionType.CHOICE_FROM_THREE;
        }
        if (target.equals(CHOICE_FROM_FOUR_CODE) || target.equals(CHOICE_FROM_FOUR_NAME)) {
            return QuestionType.CHOICE_FROM_FOUR;
        }
        if (target.equals(CHOICE_FROM_FIVE_CODE) || target.equals(CHOICE_FROM_FIVE_NAME)) {
            return QuestionType.CHOICE_FROM_FIVE;
        }
        if (target.equals(ORDERED_SUBJECTIVE_CODE) || target.equals(ORDERED_SUBJECTIVE_NAME)) {
            return QuestionType.ORDERED_SUBJECTIVE;
        }
        if (target.equals(UNORDERED_SUBJECTIVE_CODE) || target.equals(UNORDERED_SUBJECTIVE_NAME)) {
            return QuestionType.UNORDERED_SUBJECTIVE;
        }
        if (target.equals(GROUPED_SUBJECTIVE_CODE) || target.equals(GROUPED_SUBJECTIVE_NAME)) {
            return QuestionType.GROUPED_SUBJECTIVE;
        }
        if (target.equals(ESSAY_CODE) || target.equals(ESSAY_NAME)) {
            return QuestionType.ESSAY;
        }
        if (target.equals(FILLING_SELECTION_CODE) || target.equals(FILLING_SELECTION_NAME)) {
            return QuestionType.FILLING_SELECTION;
        }
        if (target.equals(DESCRIPTIVE_CODE) || target.equals(DESCRIPTIVE_NAME)) {
            return QuestionType.DESCRIPTIVE;
        }
        if (target.equals(DRAG_DROP_CODE) || target.equals(DRAG_DROP_NAME)) {
            return QuestionType.DRAG_DROP;
        }
        if (target.equals(AREA_SELECTION_CODE) || target.equals(AREA_SELECTION_NAME)) {
            return QuestionType.AREA_SELECTION;
        }
        if (target.equals(CONNECTING_LINE_CODE) || target.equals(CONNECTING_LINE_NAME)) {
            return QuestionType.CONNECTING_LINE;
        }
        if (target.equals(ETC_CODE) || target.equals(ETC_NAME)) {
            return QuestionType.ETC;
        }

        throw new QuestionTypeNotFoundException(String.format(QUESTION_TYPE_NOT_FOUND_EXCEPTION_MESSAGE, target));
    }

    // 문제 유형 없이 답만 주어진 경우 "해설 참조"를 통해 서술형과 기타를 구분 => 정답 채점 제외
    public static boolean isDescriptive(String value) {
        String target = value.trim();

        return target.equals(DESCRIPTIVE_CODE) || target.equals(DESCRIPTIVE_NAME)
                || target.equals(ETC_CODE) || target.equals(ETC_NAME) || target.equals(COMMENTARY);
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
