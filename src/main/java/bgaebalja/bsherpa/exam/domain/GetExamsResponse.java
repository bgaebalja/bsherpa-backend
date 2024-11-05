package bgaebalja.bsherpa.exam.domain;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetExamsResponse {
    private List<GetExamResponse> getExamResponses;

    private GetExamsResponse(List<GetExamResponse> getExamResponses) {
        this.getExamResponses = getExamResponses;
    }

    public static GetExamsResponse from(List<Exam> exams) {
        return new GetExamsResponse(exams.stream().map(GetExamResponse::from).collect(Collectors.toList()));
    }

    public int size() {
        return getExamResponses.size();
    }

    public GetExamResponse get(int index) {
        return getExamResponses.get(index);
    }
}
