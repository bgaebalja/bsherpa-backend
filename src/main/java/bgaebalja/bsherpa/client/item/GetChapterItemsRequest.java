package bgaebalja.bsherpa.client.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class GetChapterItemsRequest {
    private static final String MINOR_CLASSIFICATION_VALUE = "단원 코드 목록";

    private static final String LEVEL_COUNT_VALUE = "각 난이도 별 문제 개수";
    private static final String LEVEL_COUNT_EXAMPLE = "[5, 5, 5, 5, 5]";

    private static final String QUESTION_FORM_VALUE = "문제 유형";
    private static final String QUESTION_FORM_EXAMPLE = "multiple,subjective";

    private static final String ACTIVITY_CATEGORY_LIST_VALUE = "정답 URL";
    private static final String ACTIVITY_CATEGORY_LIST_EXAMPLE = "[441, 439]";

    @ApiModelProperty(value = MINOR_CLASSIFICATION_VALUE)
    private List<MinorClassficationRequest> minorClassification;

    @ApiModelProperty(value = LEVEL_COUNT_VALUE, example = LEVEL_COUNT_EXAMPLE)
    private List<Integer> levelCnt;

    @ApiModelProperty(value = QUESTION_FORM_VALUE, example = QUESTION_FORM_EXAMPLE)
    private String questionForm;

    @ApiModelProperty(value = ACTIVITY_CATEGORY_LIST_VALUE, example = ACTIVITY_CATEGORY_LIST_EXAMPLE)
    private List<Integer> activityCategoryList;
}
