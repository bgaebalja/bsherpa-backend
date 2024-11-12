package bgaebalja.bsherpa.exam.domain;

import bgaebalja.bsherpa.collection.domain.Collection;
import bgaebalja.bsherpa.collection.domain.GetCollectionsResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetExamResponse {
    private static final String DEFAULT_TIME_LIMIT = "60";

    private Long id;
    private String bookId;
    private String username;
    private String className;
    private String grade;
    private String examName;
    private String subjectName;
    private String timeLimit;
    private int size;
    private GetCollectionsResponse getCollectionsResponse;

    @Builder
    private GetExamResponse(
            Long id, String bookId,String username, String className, String grade, String examName,
            String subjectName, String timeLimit, int size, GetCollectionsResponse getCollectionsResponse
    ) {
        this.id = id;
        this.bookId = bookId;
        this.username = username;
        this.className = className;
        this.grade = grade;
        this.examName = examName;
        this.subjectName = subjectName;
        this.timeLimit = timeLimit;
        this.size = size;
        this.getCollectionsResponse = getCollectionsResponse;
    }

    public static GetExamResponse from(Exam exam) {
        return GetExamResponse.builder()
                .id(exam.getId())
                .bookId(exam.getBook().getBookId())
                .username(exam.getUser().getUsername())
                .className(exam.getUser().getClazz())
                .grade(exam.getUser().getGrade())
                .examName(exam.getExamName())
                .subjectName(exam.getBook().getSubject().getName())
                .timeLimit(DEFAULT_TIME_LIMIT)
                .size(exam.getCollections().stream().map(Collection::getQuestionCount).reduce(0, Integer::sum))
                .getCollectionsResponse(GetCollectionsResponse.from(exam.getCollections()))
                .build();
    }
}
