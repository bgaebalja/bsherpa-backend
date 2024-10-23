package bgaebalja.bsherpa.chapter.controller;

import bgaebalja.bsherpa.client.chapter.ChapterApiClient;
import bgaebalja.bsherpa.client.chapter.GetChaptersResponse;
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
@RequestMapping("/chapters/external")
@RequiredArgsConstructor
public class ChapterExternalController {
    private final ChapterApiClient chapterApiClient;

    private static final String GET_CHAPTERS_FROM_TSHERPA = "T셀파의 교재 별 단원 목록 조회";
    private static final String GET_CHAPTERS_FROM_TSHERPA_DESCRIPTION
            = "교재 ID를 입력해 T셀파의 교재 별 단원 목록을 조회할 수 있습니다.";
    private static final String TSHERPA_SUBJECT_ID = "T셀파의 교재 ID";
    private static final String TSHERPA_SUBJECT_ID_EXAMPLE = "1136";

    @GetMapping()
    @ApiOperation(value = GET_CHAPTERS_FROM_TSHERPA, notes = GET_CHAPTERS_FROM_TSHERPA_DESCRIPTION)
    public ResponseEntity<GetChaptersResponse> getItemsFromTsherpa(
            @ApiParam(value = TSHERPA_SUBJECT_ID, example = TSHERPA_SUBJECT_ID_EXAMPLE)
            @RequestParam String subjectId
    ) {
        FormatValidator.validatePositiveInteger(subjectId);

        return ResponseEntity.status(OK).body(chapterApiClient.getChapters(subjectId));
    }
}
