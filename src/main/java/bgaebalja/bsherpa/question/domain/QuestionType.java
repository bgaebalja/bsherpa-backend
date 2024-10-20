package bgaebalja.bsherpa.question.domain;

import bgaebalja.bsherpa.question.exception.QuestionTypeNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static bgaebalja.bsherpa.question.exception.ExceptionMessage.QUESTION_TYPE_NOT_FOUND_EXCEPTION_MESSAGE;

/**
 * 2지 선택: 두 개의 보기 튜플 생성
 * 5지 선택: 다섯 개의 보기 튜플 생성
 * 단답형: 보기 튜플 생성 안 함
 * 서술형: 정답 채점 제외
 */
@RequiredArgsConstructor
@Getter
public enum QuestionType {
    CHOICE_FROM_TWO("20", "2지 선택"),
    CHOICE_FROM_FIVE("50", "5지 선택"),
    SUBJECTIVE("60", "단답형"),
    DESCRIPTIVE("85", "서술형");

    private final String code;
    private final String name;

    // 서술형인 경우 정답 채점 제외
    public boolean isDescriptive() {
        return this == DESCRIPTIVE;
    }

    public static String getDescriptionByCode(String code) {
        for (QuestionType questionType : QuestionType.values()) {
            if (questionType.getCode().equals(code)) {
                return questionType.getName();
            }
        }

        throw new QuestionTypeNotFoundException(String.format(QUESTION_TYPE_NOT_FOUND_EXCEPTION_MESSAGE, code));
    }
}
