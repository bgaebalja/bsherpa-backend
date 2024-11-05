package bgaebalja.bsherpa.exam.domain;

import bgaebalja.bsherpa.collection.domain.RegisterCollectionRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Getter
public class RegisterExamRequest {
    private String email;
    private String bookId;
    private String examName;
    private Long totalCount;
    private String examType;
    private List<RegisterCollectionRequest> collections;
}
