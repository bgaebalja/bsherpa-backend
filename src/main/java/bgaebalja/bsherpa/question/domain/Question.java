package bgaebalja.bsherpa.question.domain;

import bgaebalja.bsherpa.audit.BaseGeneralEntity;
import bgaebalja.bsherpa.collection.domain.Collection;
import bgaebalja.bsherpa.util.FormatConverter;
import bgaebalja.bsherpa.util.QuestionClassifier;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static bgaebalja.bsherpa.util.EntityConstant.BOOLEAN_DEFAULT_FALSE;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Question extends BaseGeneralEntity {
    @Column(nullable = false)
    private Long itemId;

    @Column(nullable = false)
    private String descriptionUrl;

    // enum으로 저장
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @JsonDeserialize(using = QuestionTypeDeserializer.class)
    private QuestionType questionType;

    // enum으로 저장
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @JsonDeserialize(using = DifficultyDeserializer.class)
    private Difficulty difficulty;

    // 서술형인 경우 "해설 참조"로 저장, 정답 채점 대상에서 제외
    @Column(nullable = false)
    private String answer;

    @Column(nullable = false)
    private String answerUrl;

    @Column(nullable = false)
    private int errorReportCount;

    @Column(nullable = false, columnDefinition = BOOLEAN_DEFAULT_FALSE)
    private boolean blockYn;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    // 배치순서 Column 추가, Chapter -> Question 안으로 넣게 수정
    @Column(name = "placement_number")
    private Integer placementNumber;

    @Column(name = "large_chapter_code")
    private String largeChapterCode;

    @Column(name = "large_chapter_name")
    private String largeChapterName;

    @Column(name = "medium_chapter_code")
    private String mediumChapterCode;

    @Column(name = "medium_chapter_name")
    private String mediumChapterName;

    @Column(name = "small_chapter_code")
    private String smallChapterCode;

    @Column(name = "small_chapter_name")
    private String smallChapterName;

    @Column(name = "topic_chapter_code")
    private String topicChapterCode;

    @Column(name = "topic_chapter_name")
    private String topicChapterName;

    @Builder
    private Question(
            Long itemId, String descriptionUrl, QuestionType questionType,
            Difficulty difficulty, String answer, String answerUrl, Collection collection, Integer placementNumber,
            String largeChapterCode, String largeChapterName , String mediumChapterCode, String mediumChapterName,
            String smallChapterCode, String smallChapterName, String topicChapterCode, String topicChapterName
    ) {
        this.itemId = itemId;
        this.descriptionUrl = descriptionUrl;
        this.questionType = questionType;
        this.difficulty = difficulty;
        this.answer = answer;
        this.answerUrl = answerUrl;
        this.collection = collection;
        this.placementNumber = placementNumber;
        this.largeChapterCode = largeChapterCode;
        this.largeChapterName = largeChapterName;
        this.mediumChapterCode = mediumChapterCode;
        this.mediumChapterName = mediumChapterName;
        this.smallChapterCode = smallChapterCode;
        this.smallChapterName = smallChapterName;
        this.topicChapterCode = topicChapterCode;
        this.topicChapterName = topicChapterName;
    }

    public static Question from(CreateQuestionRequest createQuestionRequest, Collection collection) {
        return Question.builder()
                .itemId(FormatConverter.parseToLong(createQuestionRequest.getItemId()))
                .descriptionUrl(createQuestionRequest.getDescriptionUrl())
                .questionType(QuestionClassifier.classifyType(createQuestionRequest.getQuestionType()))
                .difficulty(QuestionClassifier.classifyDifficulty(createQuestionRequest.getDifficulty()))
                .answer(createQuestionRequest.getAnswer())
                .answerUrl(createQuestionRequest.getAnswerUrl())
                .collection(collection)
                .build();
    }
}
