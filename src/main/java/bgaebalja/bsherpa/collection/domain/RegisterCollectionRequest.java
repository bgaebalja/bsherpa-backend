package bgaebalja.bsherpa.collection.domain;

import bgaebalja.bsherpa.passage.domain.Passage;
import bgaebalja.bsherpa.question.domain.Question;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class RegisterCollectionRequest {
    private List<Passage> passages;
    private List<Question> questions;

    public Collection toEntity() {
        List<Passage> filteredPassages = passages.stream()
                .filter(passage -> !("noPassage".equals(passage.getPassageId())))
                .collect(Collectors.toList());


        Collection collection = Collection.builder()
                .passages(filteredPassages.isEmpty() ? null : filteredPassages)
                .questions(questions)
                .build();

        filteredPassages.forEach(passage -> passage.assignCollection(collection));

        if (questions != null) {
            questions.forEach(question -> question.assignCollection(collection)); // Question에도 collection 할당
        }

        return collection;
    }
}
