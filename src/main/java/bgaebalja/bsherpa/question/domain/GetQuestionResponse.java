package bgaebalja.bsherpa.question.domain;

import bgaebalja.bsherpa.option.domain.GetOptionsResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetQuestionResponse {
    private Long itemId;
    private String html;
    private String descriptionUrl;
    private QuestionType questionType;
    private GetOptionsResponse getOptionsResponse;
    private Difficulty difficulty;
    private String answer;
    private String answerUrl;
    private int errorReportCount;
    private boolean blockYn;
    private Integer placementNumber;
    private String largeChapterCode;
    private String largeChapterName;
    private String mediumChapterCode;
    private String mediumChapterName;
    private String smallChapterCode;
    private String smallChapterName;
    private String topicChapterCode;
    private String topicChapterName;
    private boolean isSubjective;

    @Builder
    private GetQuestionResponse(
            Long itemId, String html, String descriptionUrl, QuestionType questionType,
            GetOptionsResponse getOptionsResponse, Difficulty difficulty, String answer, String answerUrl,
            int errorReportCount, boolean blockYn, Integer placementNumber, String largeChapterCode,
            String largeChapterName, String mediumChapterCode, String mediumChapterName, String smallChapterCode,
            String smallChapterName, String topicChapterCode, String topicChapterName, boolean isSubjective
    ) {
        this.itemId = itemId;
        this.html = html;
        this.descriptionUrl = descriptionUrl;
        this.questionType = questionType;
        this.getOptionsResponse = getOptionsResponse;
        this.difficulty = difficulty;
        this.answer = answer;
        this.answerUrl = answerUrl;
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
        this.isSubjective = isSubjective;
    }

    public static GetQuestionResponse from(Question question) {
        boolean isSubjective = question.getQuestionType().isSubjective();
        GetOptionsResponse getOptionsResponse = null;
        if (!isSubjective) {
            getOptionsResponse = GetOptionsResponse.from(question.getOptions());
        }

        return GetQuestionResponse.builder()
                .itemId(question.getItemId())
                .html(question.getHtml())
                .descriptionUrl(question.getDescriptionUrl())
                .questionType(question.getQuestionType())
                .getOptionsResponse(getOptionsResponse)
                .difficulty(question.getDifficulty())
                .answer(question.getAnswer())
                .answerUrl(question.getAnswerUrl())
                .errorReportCount(question.getErrorReportCount())
                .blockYn(question.isBlockYn())
                .placementNumber(question.getPlacementNumber())
                .largeChapterCode(question.getLargeChapterCode())
                .largeChapterName(question.getLargeChapterName())
                .mediumChapterCode(question.getMediumChapterCode())
                .build();
    }
}
