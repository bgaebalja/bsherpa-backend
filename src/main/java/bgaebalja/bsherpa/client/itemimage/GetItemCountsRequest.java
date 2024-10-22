package bgaebalja.bsherpa.client.itemimage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class GetItemCountsRequest {
    private static final String CURRICULUM_CODE_VALUE = "커리큘럼 코드";
    private static final String CURRICULUM_CODE_EXAMPLE = "15";

    private static final String SUBJECT_ID_VALUE = "교재 ID";
    private static final String SUBJECT_ID_EXAMPLE = "1154";

    private static final String LARGE_CHAPTER_ID_VALUE = "대단원 ID";
    private static final String LARGE_CHAPTER_ID_EXAMPLE = "115401";

    private static final String MEDIUM_CHAPTER_ID_VALUE = "중단원 ID";
    private static final String MEDIUM_CHAPTER_ID_EXAMPLE = "11540101";

    private static final String SMALL_CHAPTER_ID_VALUE = "소단원 ID";
    private static final String SMALL_CHAPTER_ID_EXAMPLE = "1154010101";

    @ApiModelProperty(value = CURRICULUM_CODE_VALUE, example = CURRICULUM_CODE_EXAMPLE)
    private String curriculumCode;

    @ApiModelProperty(value = SUBJECT_ID_VALUE, example = SUBJECT_ID_EXAMPLE)
    private String subjectId;

    @ApiModelProperty(value = LARGE_CHAPTER_ID_VALUE, example = LARGE_CHAPTER_ID_EXAMPLE)
    private String largeChapterId;

    @ApiModelProperty(value = MEDIUM_CHAPTER_ID_VALUE, example = MEDIUM_CHAPTER_ID_EXAMPLE)
    private String mediumChapterId;

    @ApiModelProperty(value = SMALL_CHAPTER_ID_VALUE, example = SMALL_CHAPTER_ID_EXAMPLE)
    private String smallChapterId;
}
