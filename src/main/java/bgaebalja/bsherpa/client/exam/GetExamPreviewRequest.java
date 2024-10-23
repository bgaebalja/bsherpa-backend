package bgaebalja.bsherpa.client.exam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class GetExamPreviewRequest {
    private static final String TSHERPA_EXAM_ID = "T셀파의 시험지 ID";
    private static final String TSHERPA_EXAM_ID_EXAMPLE = "1534";

    private static final String TSHERPA_DIFFERENTIATION = "T셀파의 시험지 구성 정보";
    private static final String TSHERPA_DIFFERENTIATION_EXAMPLE = "A";

    @ApiModelProperty(value = TSHERPA_EXAM_ID, example = TSHERPA_EXAM_ID_EXAMPLE)
    private String examId;

    @ApiModelProperty(value = TSHERPA_DIFFERENTIATION, example = TSHERPA_DIFFERENTIATION_EXAMPLE)
    private String differentiation;
}
