package bgaebalja.bsherpa.question.controller;

import bgaebalja.bsherpa.client.item.*;
import bgaebalja.bsherpa.util.FormatValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/questions/external")
@RequiredArgsConstructor
public class QuestionExternalController {
    private final ItemApiClient itemApiClient;

    private static final String GET_ITEMS_FROM_TSHERPA = "T셀파의 문제 목록 조회";
    private static final String GET_ITEMS_FROM_TSHERPA_DESCRIPTION
            = "문제 ID 목록을 입력해 T셀파의 문제 목록을 조회할 수 있습니다.";
    private static final String TSHERPA_ITEM_IDS = "T셀파의 문제 ID 목록";
    private static final String TSHERPA_ITEM_IDS_EXAMPLE = "494519, 494520";

    private static final String GET_CHAPTER_ITEMS_FROM_TSHERPA = "T셀파의 단원 별 문제 목록 조회";
    private static final String GET_CHAPTER_ITEMS_FROM_TSHERPA_DESCRIPTION
            = "단원 별 문제 목록 조회 양식을 입력해 T셀파의 문제 목록을 조회할 수 있습니다.";
    private static final String GET_CHAPTER_ITEMS_FROM_TSHERPA_FORM = "단원 별 문제 목록 조회 양식";

    private static final String GET_EXAM_ITEMS_FROM_TSHERPA = "T셀파의 시험지 별 문제 목록 조회";
    private static final String GET_EXAM_ITEMS_FROM_TSHERPA_DESCRIPTION
            = "시험지 ID를 입력해 T셀파의 문제 목록을 조회할 수 있습니다.";
    private static final String TSHERPA_EXAM_ID = "T셀파의 시험지 ID";
    private static final String TSHERPA_EXAM_ID_EXAMPLE = "1534";

    private static final String GET_SIMILAR_ITEMS_FROM_TSHERPA = "T셀파의 유사 문제 목록 조회";
    private static final String GET_SIMILAR_ITEMS_FROM_TSHERPA_DESCRIPTION
            = "문제 ID 목록과 제외할 문제 ID 목록을 입력해 T셀파의 유사 문제 목록을 조회할 수 있습니다.";
    private static final String TSHERPA_EXCLUDED_ITEM_IDS = "T셀파의 제외할 문제 ID 목록";
    private static final String TSHERPA_EXCLUDED_ITEM_IDS_EXAMPLE = "206643, 259853";

    @GetMapping()
    @ApiOperation(value = GET_ITEMS_FROM_TSHERPA, notes = GET_ITEMS_FROM_TSHERPA_DESCRIPTION)
    public ResponseEntity<GetItemsResponse> getItemsFromTsherpa(
            @ApiParam(value = TSHERPA_ITEM_IDS, defaultValue = TSHERPA_ITEM_IDS_EXAMPLE)
            @RequestParam List<String> itemIds
    ) {
        itemIds.stream().forEach(FormatValidator::validatePositiveInteger);

        return ResponseEntity.status(OK).body(itemApiClient.getItems(GetItemsRequest.of(itemIds)));
    }

    @PostMapping("/chapters")
    @ApiOperation(value = GET_CHAPTER_ITEMS_FROM_TSHERPA, notes = GET_CHAPTER_ITEMS_FROM_TSHERPA_DESCRIPTION)
    public ResponseEntity<GetItemsResponse> getChapterItemsFromTsherpa(
            @ApiParam(value = GET_CHAPTER_ITEMS_FROM_TSHERPA_FORM)
            @RequestBody GetChapterItemsRequest getChapterItemsRequest
    ) {
        getChapterItemsRequest.getLevelCnt().stream().forEach(FormatValidator::validatePositiveInteger);
        getChapterItemsRequest.getActivityCategoryList().stream().forEach(FormatValidator::validatePositiveInteger);

        return ResponseEntity.status(OK).body(itemApiClient.getChapterItems(getChapterItemsRequest));
    }

    @GetMapping("/exam")
    @ApiOperation(value = GET_EXAM_ITEMS_FROM_TSHERPA, notes = GET_EXAM_ITEMS_FROM_TSHERPA_DESCRIPTION)
    public ResponseEntity<GetItemsResponse> getExamItemsFromTsherpa(
            @ApiParam(value = TSHERPA_EXAM_ID, example = TSHERPA_EXAM_ID_EXAMPLE)
            @RequestParam String examId
    ) {
        FormatValidator.validatePositiveInteger(examId);

        return ResponseEntity.status(OK).body(itemApiClient.getExamItems(examId));
    }

    @GetMapping("/similar-items")
    @ApiOperation(value = GET_SIMILAR_ITEMS_FROM_TSHERPA, notes = GET_SIMILAR_ITEMS_FROM_TSHERPA_DESCRIPTION)
    public ResponseEntity<GetItemsResponse> getSimilarItemsFromTsherpa(
            @ApiParam(value = TSHERPA_ITEM_IDS, defaultValue = TSHERPA_ITEM_IDS_EXAMPLE)
            @RequestParam List<String> itemIds,
            @ApiParam(value = TSHERPA_EXCLUDED_ITEM_IDS, defaultValue = TSHERPA_EXCLUDED_ITEM_IDS_EXAMPLE)
            @RequestParam List<String> excludedItemIds
    ) {
        itemIds.stream().forEach(FormatValidator::validatePositiveInteger);
        excludedItemIds.stream().forEach(FormatValidator::validatePositiveInteger);

        return ResponseEntity.status(OK).body(
                itemApiClient.getSimilarItems(GetSimilarItemsRequest.of(itemIds, excludedItemIds))
        );
    }
}
