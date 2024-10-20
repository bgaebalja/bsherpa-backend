package bgaebalja.bsherpa.question.domain;

import bgaebalja.bsherpa.audit.BaseGeneralEntity;
import bgaebalja.bsherpa.collection.domain.Collection;
import bgaebalja.bsherpa.util.FormatConverter;
import bgaebalja.bsherpa.util.QuestionClassifier;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static bgaebalja.bsherpa.util.EntityConstant.BOOLEAN_DEFAULT_FALSE;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Question extends BaseGeneralEntity {
    @Column(nullable = false)
    private Long itemId;

    @Column(nullable = false)
    private String answerUrl;

    @Column(nullable = false)
    private String descriptionUrl;

    // enum으로 저장
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private QuestionType questionType;

    // enum으로 저장
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Difficulty difficulty;

    // 서술형인 경우 "해설 참조"로 저장, 정답 채점 대상에서 제외
    @Column(nullable = false)
    private String answer;

    @Column(nullable = false)
    private int errorReportCount;

    @Column(nullable = false, columnDefinition = BOOLEAN_DEFAULT_FALSE)
    private boolean blockYn;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @Builder
    private Question(
            Long itemId, String answerUrl, String descriptionUrl, QuestionType questionType,
            Difficulty difficulty, String answer, Collection collection
    ) {
        this.itemId = itemId;
        this.answerUrl = answerUrl;
        this.descriptionUrl = descriptionUrl;
        this.questionType = questionType;
        this.difficulty = difficulty;
        this.answer = answer;
        this.collection = collection;
    }

    public static Question from(CreateQuestionRequest createQuestionRequest, Collection collection) {
        return Question.builder()
                .itemId(FormatConverter.parseToLong(createQuestionRequest.getItemId()))
                .answerUrl(createQuestionRequest.getAnswerUrl())
                .descriptionUrl(createQuestionRequest.getDescriptionUrl())
                .questionType(QuestionClassifier.classifyType(createQuestionRequest.getQuestionType()))
                .difficulty(QuestionClassifier.classifyDifficulty(createQuestionRequest.getDifficulty()))
                .answer(createQuestionRequest.getAnswer())
                .collection(collection)
                .build();
    }
}
