package bgaebalja.bsherpa.file.domain;

import bgaebalja.bsherpa.erorreport.domain.RegisterErrorReportRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import static bgaebalja.bsherpa.file.domain.TargetType.ERROR_REPORT;
import static bgaebalja.bsherpa.util.RequestConstant.ID_EXAMPLE;

@NoArgsConstructor
@Getter
@Setter
public class AddFileRequest {
    private static final String FILE_VALUE = "파일";

    private static final String TARGET_TYPE_VALUE = "대상 타입";
    private static final String TARGET_TYPE_EXAMPLE = "POST";

    private static final String TARGET_ID_VALUE = "대상 ID";

    @ApiModelProperty(value = FILE_VALUE)
    private MultipartFile file;

    @ApiModelProperty(value = TARGET_TYPE_VALUE, example = TARGET_TYPE_EXAMPLE)
    private String targetType;

    @ApiModelProperty(value = TARGET_ID_VALUE, example = ID_EXAMPLE)
    private String targetId;

    private AddFileRequest(MultipartFile file, String targetType, String targetId) {
        this.file = file;
        this.targetType = targetType;
        this.targetId = targetId;
    }

    public static AddFileRequest from(RegisterErrorReportRequest registerErrorReportRequest, String id) {
        return new AddFileRequest(registerErrorReportRequest.getImage(), ERROR_REPORT.name(), id);
    }
}
