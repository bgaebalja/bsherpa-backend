package bgaebalja.bsherpa.client.chapter;

import lombok.Getter;

@Getter
public class GetChapterResponse {
    private String curriculumCode;
    private String curriculumName;
    private Long subjectId;
    private String subjectName;
    private Long largeChapterId;
    private String largeChapterName;
    private Long mediumChapterId;
    private String mediumChapterName;
    private Long smallChapterId;
    private String smallChapterName;
    private Long topicChapterId;
    private String topicChapterName;
}
