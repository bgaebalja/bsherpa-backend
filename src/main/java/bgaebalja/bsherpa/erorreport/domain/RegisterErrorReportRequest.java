package bgaebalja.bsherpa.erorreport.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class RegisterErrorReportRequest {
    private static final String TYPE_VALUE = "오류 신고 유형";
    private static final String TYPE_EXAMPLE = "문제오류";

    private static final String IMAGE_VALUE = "이미지";

    private static final String CONTENT_VALUE = "신고 내용";
    private static final String CONTENT_EXAMPLE = "문제가 잘못되었습니다.";

    @ApiModelProperty(value = TYPE_VALUE, example = TYPE_EXAMPLE)
    private String type;

    @ApiModelProperty(value = IMAGE_VALUE)
    private MultipartFile image;

    @ApiModelProperty(value = CONTENT_VALUE, example = CONTENT_EXAMPLE)
    private String content;
}
