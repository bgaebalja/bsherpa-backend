package bgaebalja.bsherpa.question.domain;

import bgaebalja.bsherpa.question.exception.QuestionTypeNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static bgaebalja.bsherpa.question.exception.ExceptionMessage.QUESTION_TYPE_NOT_FOUND_EXCEPTION_MESSAGE;

/**
 * 자유선지형: 선택지 개수만큼의 보기 튜플 생성
 * 2지 선택: 두 개의 보기 튜플 생성
 * 3지 선택: 세 개의 보기 튜플 생성
 * 4지 선택: 네 개의 보기 튜플 생성
 * 5지 선택: 다섯 개의 보기 튜플 생성
 * 단답형, 논술형, 선택채움형, 서술형, 드래그 드랍, 영역 선택형, 선잇기형, 기타: 보기 튜플 생성 안 함
 * 서술형, 기타: 정답 채점 제외
 */
@RequiredArgsConstructor
@Getter
public enum QuestionType {
    FREE_CHOICE("10", "자유선지형"),
    CHOICE_FROM_TWO("20", "2지 선택"),
    CHOICE_FROM_THREE("30", "3지 선택"),
    CHOICE_FROM_FOUR("40", "4지 선택"),
    CHOICE_FROM_FIVE("50", "5지 선택"),
    ORDERED_SUBJECTIVE("60", "단답 유순형"),
    UNORDERED_SUBJECTIVE("61", "단답 무순형"),
    GROUPED_SUBJECTIVE("63", "단답 그룹형"),
    ESSAY("70", "논술형"),
    FILLING_SELECTION("84", "선택채움형"),
    DESCRIPTIVE("85", "서술형"),
    DRAG_DROP("86", "드래그 드랍"),
    AREA_SELECTION("87", "영역 선택형"),
    CONNECTING_LINE("88", "선잇기형"),
    ETC("99", "기타");

    private final String code;
    private final String name;

    // 서술형인 경우 정답 채점 제외
    public boolean isDescriptive() {
        return this == DESCRIPTIVE;
    }

    public boolean isSubjective() {
        return Integer.parseInt(this.getCode()) > 50;
    }

    public static String getNameByCode(String code) {
        for (QuestionType questionType : QuestionType.values()) {
            if (questionType.getCode().equals(code)) {
                return questionType.getName();
            }
        }

        throw new QuestionTypeNotFoundException(String.format(QUESTION_TYPE_NOT_FOUND_EXCEPTION_MESSAGE, code));
    }
}
