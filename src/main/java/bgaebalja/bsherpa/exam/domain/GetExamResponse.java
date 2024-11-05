package bgaebalja.bsherpa.exam.domain;

import bgaebalja.bsherpa.collection.domain.Collection;
import bgaebalja.bsherpa.collection.domain.GetCollectionsResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetExamResponse {
    private Long id;
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
            Long id, String username, String className, String grade, String examName,
            String subjectName, String timeLimit, int size, GetCollectionsResponse getCollectionsResponse
    ) {
        this.id = id;
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
        // TODO: 시간 제한 학년/과목 별로 연동
        // TODO: 회원에 추가되는 학교 등급과 학년 정보 연동
        return GetExamResponse.builder()
                .id(exam.getId())
                .username(exam.getUser().getUsername())
//                .className(exam.getUser().getClassName())
//                .grade(exam.getUser().getGrade())
                .className("중")
                .grade("3")
                .examName(exam.getExamName())
                .subjectName(exam.getBook().getSubject().getName())
                .timeLimit("60")
                .size(exam.getCollections().stream().map(Collection::getQuestionCount).reduce(0, Integer::sum))
                .getCollectionsResponse(GetCollectionsResponse.from(exam.getCollections()))
                .build();
    }
}
