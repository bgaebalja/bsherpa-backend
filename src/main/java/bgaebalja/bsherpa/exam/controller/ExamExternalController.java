package bgaebalja.bsherpa.exam.controller;

import bgaebalja.bsherpa.client.exam.ExamApiClient;
import bgaebalja.bsherpa.client.exam.GetExamPreviewRequest;
import bgaebalja.bsherpa.client.exam.GetItemClassificationPreviewsResponse;
import bgaebalja.bsherpa.client.exam.GetPreviewResponse;
import bgaebalja.bsherpa.util.FormatValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/exams/external/previews")
@RequiredArgsConstructor
public class ExamExternalController {
    private final ExamApiClient examApiClient;

    private static final String GET_EXAM_PREVIEW_FROM_TSHERPA = "T셀파의 시험지 미리보기 주소 조회";
    private static final String GET_EXAM_PREVIEW_FROM_TSHERPA_DESCRIPTION
            = "시험지 ID와 시험지 구성 정보를 입력해 T셀파의 시험지 미리보기 주소를 조회할 수 있습니다.";
    private static final String GET_EXAM_PREVIEW_FROM_TSHERPA_FORM = "T셀파의 시험지 미리보기 주소 조회 요청 양식";

    private static final String GET_ITEM_CLASSIFICATION_PREVIEW_FROM_TSHERPA = "T셀파의 시험지 문항분류표 미리보기 조회";
    private static final String GET_ITEM_CLASSIFICATION_PREVIEW_FROM_TSHERPA_DESCRIPTION
            = "시험지 ID와 시험지 구성 정보를 입력해 T셀파의 시험지 문항분류표 미리보기를 조회할 수 있습니다.";

    private static final String TSHERPA_EXAM_ID = "T셀파의 시험지 ID";
    private static final String TSHERPA_EXAM_ID_EXAMPLE = "1534";

    @PostMapping()
    @ApiOperation(value = GET_EXAM_PREVIEW_FROM_TSHERPA, notes = GET_EXAM_PREVIEW_FROM_TSHERPA_DESCRIPTION)
    public ResponseEntity<GetPreviewResponse> getPreviewFromTsherpa(
            @ApiParam(value = GET_EXAM_PREVIEW_FROM_TSHERPA_FORM)
            @RequestBody GetExamPreviewRequest getExamPreviewRequest
    ) {
        FormatValidator.validatePositiveInteger(getExamPreviewRequest.getExamId());

        return ResponseEntity.status(OK).body(examApiClient.getPreview(getExamPreviewRequest));
    }

    @GetMapping("/item-classifications")
    @ApiOperation(
            value = GET_ITEM_CLASSIFICATION_PREVIEW_FROM_TSHERPA,
            notes = GET_ITEM_CLASSIFICATION_PREVIEW_FROM_TSHERPA_DESCRIPTION
    )
    public ResponseEntity<GetItemClassificationPreviewsResponse> getItemClassificationPreviewFromTsherpa(
            @ApiParam(value = TSHERPA_EXAM_ID, example = TSHERPA_EXAM_ID_EXAMPLE)
            @RequestParam String examId
    ) {
        FormatValidator.validatePositiveInteger(examId);

        return ResponseEntity.status(OK).body(examApiClient.getItemClassificationPreview(examId));
    }
}
