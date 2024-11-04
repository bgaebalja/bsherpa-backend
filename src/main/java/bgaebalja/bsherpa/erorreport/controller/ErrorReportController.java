package bgaebalja.bsherpa.erorreport.controller;

import bgaebalja.bsherpa.erorreport.domain.RegisterErrorReportRequest;
import bgaebalja.bsherpa.erorreport.service.ErrorReportService;
import bgaebalja.bsherpa.util.FormatValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/error-reports")
@RequiredArgsConstructor
public class ErrorReportController {
    private final ErrorReportService errorReportService;

    private static final String REGISTER_ERROR_REPORT = "문항 오류 신고 등록";
    private static final String REGISTER_ERROR_REPORT_DESCRIPTION
            = "오류 유형과 오류 이미지, 오류 신고 내용을 입력해 문항 오류 신고를 등록할 수 있습니다.";
    private static final String REGISTER_ERROR_REPORT_FORM = "문항 오류 신고 등록 양식";

    @PostMapping()
    @ApiOperation(value = REGISTER_ERROR_REPORT, notes = REGISTER_ERROR_REPORT_DESCRIPTION)
    public ResponseEntity<Void> registerQuestion(
            @ApiParam(value = REGISTER_ERROR_REPORT_FORM)
            @ModelAttribute RegisterErrorReportRequest registerErrorReportRequest
    ) {
        FormatValidator.validateEmail(registerErrorReportRequest.getUserEmail());
        FormatValidator.validatePositiveOrZeroInteger(registerErrorReportRequest.getItemId());
        errorReportService.createErrorReport(registerErrorReportRequest);

        return ResponseEntity.status(CREATED).build();
    }
}
