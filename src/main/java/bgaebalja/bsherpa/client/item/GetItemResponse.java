package bgaebalja.bsherpa.client.item;

import lombok.Getter;

@Getter
public class GetItemResponse {
    private Integer itemNo;
    private Long itemId;
    private String questionFormCode;
    private String questionFormName;
    private String difficultyCode;
    private String difficultyName;
    private Long largeChapterId;
    private String largeChapterName;
    private Long mediumChapterId;
    private String mediumChapterName;
    private Long smallChapterId;
    private String smallChapterName;
    private Long topicChapterId;
    private String topicChapterName;
    private Long passageId;
    private String passage;
    private String passageHtml;
    private String question;
    private String questionHtml;
    private String choice1Html;
    private String choice2Html;
    private String choice3Html;
    private String choice4Html;
    private String choice5Html;
    private String answer;
    private String answerHtml;
    private String explain;
    private String explainHtml;
}
