package bgaebalja.bsherpa.client.exam;

import lombok.Getter;

@Getter
public class GetItemClassificationPreviewResponse {
    private int itemNo;
    private Long itemId;
    private String difficultyName;
    private String largeChapterName;
    private String topicChapterName;
    private String answer;
    private String activityAreaName;
    private String contentAreaName;
    private String curriculumCompetencyName;
    private String achievementCode;
}
