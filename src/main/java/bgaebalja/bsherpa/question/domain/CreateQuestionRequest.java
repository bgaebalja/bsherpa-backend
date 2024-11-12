package bgaebalja.bsherpa.question.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import static bgaebalja.bsherpa.util.RequestConstant.ITEM_ID_EXAMPLE;
import static bgaebalja.bsherpa.util.RequestConstant.ITEM_ID_VALUE;

/**
 * 모두 String 타입으로 받은 후 타입 변환
 */
@Getter
public class CreateQuestionRequest {
    private static final String DESCRIPTION_URL_VALUE = "해설 URL";
    private static final String DESCRIPTION_URL_EXAMPLE
            = "https://ddipddipddip.s3.amazonaws.com/post/1819/1729131706204_Loopy2.png?w=248&fit=crop&auto=format";

    private static final String QUESTION_TYPE_NAME_VALUE = "문제 유형";
    private static final String QUESTION_TYPE_NAME_EXAMPLE = "5지 선택";

    private static final String DIFFICULTY_NAME_VALUE = "난이도";
    private static final String DIFFICULTY_NAME_EXAMPLE = "상";

    private static final String ANSWER_VALUE = "정답";
    private static final String ANSWER_EXAMPLE = "1";

    private static final String ANSWER_URL_VALUE = "정답 URL";
    private static final String ANSWER_URL_EXAMPLE
            = "https://ddipddipddip.s3.amazonaws.com/post/1819/1729131706204_Loopy2.png?w=248&fit=crop&auto=format";

    @ApiModelProperty(value = ITEM_ID_VALUE, example = ITEM_ID_EXAMPLE)
    private String itemId;

    @ApiModelProperty(value = DESCRIPTION_URL_VALUE, example = DESCRIPTION_URL_EXAMPLE)
    private String descriptionUrl;

    @ApiModelProperty(value = QUESTION_TYPE_NAME_VALUE, example = QUESTION_TYPE_NAME_EXAMPLE)
    private String questionType;

    @ApiModelProperty(value = DIFFICULTY_NAME_VALUE, example = DIFFICULTY_NAME_EXAMPLE)
    private String difficulty;

    @ApiModelProperty(value = ANSWER_VALUE, example = ANSWER_EXAMPLE)
    private String answer;

    @ApiModelProperty(value = ANSWER_URL_VALUE, example = ANSWER_URL_EXAMPLE)
    private String answerUrl;
}
