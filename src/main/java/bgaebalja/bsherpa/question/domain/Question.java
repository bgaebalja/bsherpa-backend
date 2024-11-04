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
    // Question Data
    @Column(nullable = false)
    private Long itemId;

    @Column(nullable = false)
    private String html;

    @Column(nullable = false)
    private String url;

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

    @Column(name = "description_url", nullable = false)
    private String descriptionUrl;

    @Column(name = "description_html", nullable = false)
    private String descriptionHtml;

    // Answer Data
    @Column(nullable = false)
    private String answer;

    @Column(nullable = false)
    private String answerUrl;

    @Column(nullable = false)
    private String answerHtml;

    @Column(nullable = false)
    private int errorReportCount;

    @Column(nullable = false, columnDefinition = BOOLEAN_DEFAULT_FALSE)
    private boolean blockYn;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    // 배치순서 Column 추가
    @Column(name = "placement_number")
    private Integer placementNumber;

    //Chapter Data
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
    private Question(Long itemId, String html, String url, QuestionType questionType, Difficulty difficulty, String answer,
                     String descriptionUrl, String descriptionHtml, String answerUrl, String answerHtml, int errorReportCount,
                     boolean blockYn, Integer placementNumber, String largeChapterCode, String largeChapterName, String mediumChapterCode,
                     String mediumChapterName, String smallChapterCode, String smallChapterName, String topicChapterCode,
                     String topicChapterName, Collection collection){
        this.itemId = itemId;
        this.html = html;
        this.url = url;
        this.questionType = questionType;
        this.difficulty = difficulty;
        this.descriptionUrl = descriptionUrl;
        this.descriptionHtml = descriptionHtml;
        this.answer = answer;
        this.answerUrl = answerUrl;
        this.answerHtml = answerHtml;
        this.errorReportCount = errorReportCount;
        this.blockYn = blockYn;
        this.placementNumber = placementNumber;
        this.largeChapterCode = largeChapterCode;
        this.largeChapterName = largeChapterName;
        this.mediumChapterCode = mediumChapterCode;
        this.mediumChapterName = mediumChapterName;
        this.smallChapterCode = smallChapterCode;
        this.smallChapterName = smallChapterName;
        this.topicChapterCode = topicChapterCode;
        this.topicChapterName = topicChapterName;
        this.collection = collection;
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

    public void assignCollection(Collection collection) {
        this.collection = collection;
    }
}
