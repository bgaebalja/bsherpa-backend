package bgaebalja.bsherpa.question.domain;

import bgaebalja.bsherpa.collection.domain.Collection;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetQuestionResponse {
    private Long itemId;
    private String html;
    private String descriptionUrl;
    private QuestionType questionType;
    private Difficulty difficulty;
    private String answer;
    private String answerUrl;
    private int errorReportCount;
    private boolean blockYn;
    private Collection collection;
    private Integer placementNumber;
    private String largeChapterCode;
    private String largeChapterName;
    private String mediumChapterCode;
    private String mediumChapterName;
    private String smallChapterCode;
    private String smallChapterName;
    private String topicChapterCode;
    private String topicChapterName;

    @Builder
    private GetQuestionResponse(
            Long itemId, String html, String descriptionUrl, QuestionType questionType, Difficulty difficulty,
            String answer, String answerUrl, int errorReportCount, boolean blockYn, Collection collection,
            Integer placementNumber, String largeChapterCode, String largeChapterName, String mediumChapterCode,
            String mediumChapterName, String smallChapterCode, String smallChapterName, String topicChapterCode,
            String topicChapterName
    ) {
        this.itemId = itemId;
        this.html = html;
        this.descriptionUrl = descriptionUrl;
        this.questionType = questionType;
        this.difficulty = difficulty;
        this.answer = answer;
        this.answerUrl = answerUrl;
        this.errorReportCount = errorReportCount;
        this.blockYn = blockYn;
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

    public static GetQuestionResponse from(Question question) {
        return GetQuestionResponse.builder()
                .itemId(question.getItemId())
                .html(question.getHtml())
                .descriptionUrl(question.getDescriptionUrl())
                .questionType(question.getQuestionType())
                .difficulty(question.getDifficulty())
                .answer(question.getAnswer())
                .answerUrl(question.getAnswerUrl())
                .errorReportCount(question.getErrorReportCount())
                .blockYn(question.isBlockYn())
                .collection(question.getCollection())
                .placementNumber(question.getPlacementNumber())
                .largeChapterCode(question.getLargeChapterCode())
                .largeChapterName(question.getLargeChapterName())
                .mediumChapterCode(question.getMediumChapterCode())
                .build();
    }
}
