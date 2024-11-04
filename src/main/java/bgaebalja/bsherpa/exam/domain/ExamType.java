package bgaebalja.bsherpa.exam.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExamType {
    ALL("A"),
    QUESTION("Q"),
    EXPLAIN("E");

    private final String differentiation;

    public static String getDifferentiation(String differentiation) {
        for (ExamType examType : ExamType.values()) {
            if (examType.differentiation.equals(differentiation)) {
                return examType.name();
            }
        }
        throw new IllegalArgumentException();
    }


}
