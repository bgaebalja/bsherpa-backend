package bgaebalja.bsherpa.book.controller;

import bgaebalja.bsherpa.client.chapter.*;
import bgaebalja.bsherpa.util.FormatValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/books/external")
@RequiredArgsConstructor
public class BookExternalController {
    private final ChapterApiClient chapterApiClient;

    private static final String GET_SUBJECT_FROM_TSHERPA = "T셀파의 교재 정보 조회";
    private static final String GET_SUBJECT_FROM_TSHERPA_DESCRIPTION
            = "교재 ID를 입력해 T셀파의 교재 정보를 조회할 수 있습니다.";

    private static final String GET_SUBJECT_CHAPTERS_FROM_TSHERPA = "T셀파의 교재 별 단원 목록 조회";
    private static final String GET_SUBJECT_CHAPTERS_FROM_TSHERPA_DESCRIPTION
            = "교재 ID를 입력해 T셀파의 교재 별 단원 목록을 조회할 수 있습니다.";

    private static final String GET_SUBJECT_EVALUATIONS_FROM_TSHERPA = "T셀파의 교재 별 평가 영역 목록 조회";
    private static final String GET_SUBJECT_EVALUATIONS_FROM_TSHERPA_DESCRIPTION
            = "교재 ID를 입력해 T셀파의 교재 별 평가 영역 목록을 조회할 수 있습니다.";

    private static final String GET_BOOK_EXAMS_FROM_TSHERPA = "T셀파의 교재 별 시험지 목록 조회";
    private static final String GET_BOOK_EXAMS_FROM_TSHERPA_DESCRIPTION
            = "교재 ID를 입력해 T셀파의 교재 별 시험지 목록을 조회할 수 있습니다.";

    private static final String TSHERPA_SUBJECT_ID = "T셀파의 교재 ID";
    private static final String TSHERPA_SUBJECT_ID_EXAMPLE = "1136";

    @GetMapping()
    @ApiOperation(value = GET_SUBJECT_FROM_TSHERPA, notes = GET_SUBJECT_FROM_TSHERPA_DESCRIPTION)
    public ResponseEntity<GetBookResponse> getSubjectFromTsherpa(
            @ApiParam(value = TSHERPA_SUBJECT_ID, example = TSHERPA_SUBJECT_ID_EXAMPLE)
            @RequestParam String subjectId
    ) {
        FormatValidator.validatePositiveOrZeroInteger(subjectId);

        return ResponseEntity.status(OK).body(chapterApiClient.getBook(subjectId));
    }

    @GetMapping("/chapters")
    @ApiOperation(value = GET_SUBJECT_CHAPTERS_FROM_TSHERPA, notes = GET_SUBJECT_CHAPTERS_FROM_TSHERPA_DESCRIPTION)
    public ResponseEntity<GetChaptersResponse> getSubjectChpatersFromTsherpa(
            @ApiParam(value = TSHERPA_SUBJECT_ID, example = TSHERPA_SUBJECT_ID_EXAMPLE)
            @RequestParam String subjectId
    ) {
        FormatValidator.validatePositiveOrZeroInteger(subjectId);

        return ResponseEntity.status(OK).body(chapterApiClient.getChapters(subjectId));
    }

    @GetMapping("/evaluations")
    @ApiOperation(
            value = GET_SUBJECT_EVALUATIONS_FROM_TSHERPA, notes = GET_SUBJECT_EVALUATIONS_FROM_TSHERPA_DESCRIPTION
    )
    public ResponseEntity<GetEvaluationsResponse> getSubjectEvaluationsFromTsherpa(
            @ApiParam(value = TSHERPA_SUBJECT_ID, example = TSHERPA_SUBJECT_ID_EXAMPLE)
            @RequestParam String subjectId
    ) {
        FormatValidator.validatePositiveOrZeroInteger(subjectId);

        return ResponseEntity.status(OK).body(chapterApiClient.getEvaluations(subjectId));
    }

    @GetMapping("/exams")
    @ApiOperation(value = GET_BOOK_EXAMS_FROM_TSHERPA, notes = GET_BOOK_EXAMS_FROM_TSHERPA_DESCRIPTION)
    public ResponseEntity<GetExamsResponse> getSubjectExamsFromTsherpa(
            @ApiParam(value = TSHERPA_SUBJECT_ID, example = TSHERPA_SUBJECT_ID_EXAMPLE)
            @RequestParam String subjectId
    ) {
        FormatValidator.validatePositiveOrZeroInteger(subjectId);

        return ResponseEntity.status(OK).body(chapterApiClient.getExams(subjectId));
    }
}
