package bgaebalja.bsherpa.client.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class MinorClassficationRequest {
    private static final String SUBJECT_CODE_VALUE = "과목 코드";
    private static final String SUBJECT_CODE_EXAMPLE = "1154";
    private static final String LARGE_CODE_VALUE = "대단원 코드";
    private static final String LARGE_CODE_EXAMPLE = "115401";
    private static final String MEDIUM_CODE_VALUE = "중단원 코드";
    private static final String MEDIUM_CODE_EXAMPLE = "11540101";
    private static final String SMALL_CODE_VALUE = "소단원 코드";
    private static final String SMALL_CODE_EXAMPLE = "1154010101";
    private static final String TOPIC_CODE_VALUE = "토픽단원 코드";
    private static final String TOPIC_CODE_EXAMPLE = "115401010101";

    @ApiModelProperty(value = SUBJECT_CODE_VALUE, example = SUBJECT_CODE_EXAMPLE)
    private String subject;

    @ApiModelProperty(value = LARGE_CODE_VALUE, example = LARGE_CODE_EXAMPLE)
    private String large;

    @ApiModelProperty(value = MEDIUM_CODE_VALUE, example = MEDIUM_CODE_EXAMPLE)
    private String medium;

    @ApiModelProperty(value = SMALL_CODE_VALUE, example = SMALL_CODE_EXAMPLE)
    private String small;

    @ApiModelProperty(value = TOPIC_CODE_VALUE, example = TOPIC_CODE_EXAMPLE)
    private String topic;
}
