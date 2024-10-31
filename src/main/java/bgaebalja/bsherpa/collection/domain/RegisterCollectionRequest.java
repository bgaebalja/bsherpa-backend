package bgaebalja.bsherpa.collection.domain;

import bgaebalja.bsherpa.passage.domain.Passage;
import bgaebalja.bsherpa.question.domain.Question;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RegisterCollectionRequest {
    private List<Passage> passages;
    private List<Question> questions;

    public Collection toEntity(){
        return Collection.builder()
                .passages(passages)
                .questions(questions)
                .build();
    }
}
