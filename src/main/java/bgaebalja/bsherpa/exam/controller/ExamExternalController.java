package bgaebalja.bsherpa.exam.controller;

import bgaebalja.bsherpa.client.exam.ExamApiClient;
import bgaebalja.bsherpa.client.exam.GetExamPreviewRequest;
import bgaebalja.bsherpa.client.exam.GetPreviewResponse;
import bgaebalja.bsherpa.util.FormatValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/exams/external")
@RequiredArgsConstructor
public class ExamExternalController {
    private final ExamApiClient examApiClient;

    private static final String GET_EXAM_PREVIEW_FROM_TSHERPA = "T셀파의 시험지 미리보기 주소 조회";
    private static final String GET_EXAM_PREVIEW_FROM_TSHERPA_DESCRIPTION
            = "시험지 ID와 시험지 구성 정보를 입력해 T셀파의 시험지 미리보기 주소를 조회할 수 있습니다.";
    private static final String GET_EXAM_PREVIEW_FROM_TSHERPA_FORM = "T셀파의 시험지 미리보기 주소 조회 요청 양식";

    @PostMapping()
    @ApiOperation(value = GET_EXAM_PREVIEW_FROM_TSHERPA, notes = GET_EXAM_PREVIEW_FROM_TSHERPA_DESCRIPTION)
    public ResponseEntity<GetPreviewResponse> getPreviewFromTsherpa(
            @ApiParam(value = GET_EXAM_PREVIEW_FROM_TSHERPA_FORM)
            @RequestBody GetExamPreviewRequest getExamPreviewRequest
    ) {
        FormatValidator.validatePositiveInteger(getExamPreviewRequest.getExamId());

        return ResponseEntity.status(OK).body(examApiClient.getPreview(getExamPreviewRequest));
    }
}
