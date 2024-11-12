package bgaebalja.bsherpa.question.controller;

import bgaebalja.bsherpa.client.item.GetChapterItemsRequest;
import bgaebalja.bsherpa.client.item.GetItemsRequest;
import bgaebalja.bsherpa.client.item.GetSimilarItemsRequest;
import bgaebalja.bsherpa.client.itemimage.GetItemImagesResponse;
import bgaebalja.bsherpa.client.itemimage.ItemImageApiClient;
import bgaebalja.bsherpa.util.FormatValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/question-images/external")
@RequiredArgsConstructor
public class QuestionImageExternalController {
    private final ItemImageApiClient itemImageApiClient;

    private static final String GET_ITEM_IMAGES_FROM_TSHERPA = "T셀파의 문제 이미지 목록 조회";
    private static final String GET_ITEM_IMAGES_FROM_TSHERPA_DESCRIPTION
            = "문제 ID 목록을 입력해 T셀파의 문제 이미지 목록을 조회할 수 있습니다.";
    private static final String TSHERPA_ITEM_IDS = "T셀파의 문제 ID 목록";
    private static final String TSHERPA_ITEM_IDS_EXAMPLE = "494519, 494520";

    private static final String GET_CHAPTER_ITEM_IMAGES_FROM_TSHERPA = "T셀파의 단원 별 문제 이미지 목록 조회";
    private static final String GET_CHAPTER_ITEM_IMAGES_FROM_TSHERPA_DESCRIPTION
            = "단원 별 문제 목록 조회 양식을 입력해 T셀파의 문제 이미지 목록을 조회할 수 있습니다.";

    private static final String GET_ADJUSTED_CHAPTER_ITEM_IMAGES_FROM_TSHERPA = "T셀파의 조정된 단원 별 문제 이미지 목록 조회";
    private static final String GET_ADJUSTED_CHAPTER_ITEM_IMAGES_FROM_TSHERPA_DESCRIPTION
            = "단원 별 문제 목록 조회 양식을 입력해 T셀파의 요청 난이도 개수 별로 조정된 문제 이미지 목록을 조회할 수 있습니다.";

    private static final String GET_CHAPTER_ITEM_IMAGES_FROM_TSHERPA_FORM
            = "단원 코드 목록, 각 난이도 별 문제 개수, 문제 유형, 평가 영역 ID 목록";

    private static final String GET_EXAM_ITEM_IMAGES_FROM_TSHERPA = "T셀파의 시험지 별 문제 이미지 목록 조회";
    private static final String GET_EXAM_ITEM_IMAGES_FROM_TSHERPA_DESCRIPTION
            = "시험지 ID를 입력해 T셀파의 문제 이미지 목록을 조회할 수 있습니다.";
    private static final String TSHERPA_EXAM_ID = "T셀파의 시험지 ID";
    private static final String TSHERPA_EXAM_ID_EXAMPLE = "1534";

    private static final String GET_SIMILAR_ITEM_IMAGES_FROM_TSHERPA = "T셀파의 유사 문제 이미지 목록 조회";
    private static final String GET_SIMILAR_ITEM_IMAGES_FROM_TSHERPA_DESCRIPTION
            = "문제 ID 목록과 제외할 문제 ID 목록을 입력해 T셀파의 유사 문제 이미지 목록을 조회할 수 있습니다.";
    private static final String TSHERPA_EXCLUDED_ITEM_IDS = "T셀파의 제외할 문제 ID 목록";
    private static final String TSHERPA_EXCLUDED_ITEM_IDS_EXAMPLE = "206643, 259853";

    @GetMapping()
    @ApiOperation(value = GET_ITEM_IMAGES_FROM_TSHERPA, notes = GET_ITEM_IMAGES_FROM_TSHERPA_DESCRIPTION)
    public ResponseEntity<GetItemImagesResponse> getItemImagesFromTsherpa(
            @ApiParam(value = TSHERPA_ITEM_IDS, defaultValue = TSHERPA_ITEM_IDS_EXAMPLE)
            @RequestParam List<String> itemIds
    ) {
        itemIds.stream().forEach(FormatValidator::validatePositiveOrZeroInteger);

        return ResponseEntity.status(OK).body(itemImageApiClient.getItemImages(GetItemsRequest.of(itemIds)));
    }

    @PostMapping("/chapters")
    @ApiOperation(value = GET_CHAPTER_ITEM_IMAGES_FROM_TSHERPA, notes = GET_CHAPTER_ITEM_IMAGES_FROM_TSHERPA_DESCRIPTION)
    public ResponseEntity<GetItemImagesResponse> getChapterItemImagesFromTsherpa(
            @ApiParam(value = GET_CHAPTER_ITEM_IMAGES_FROM_TSHERPA_FORM)
            @RequestBody GetChapterItemsRequest getChapterItemsRequest
    ) {
        getChapterItemsRequest.getLevelCnt().stream().forEach(FormatValidator::validatePositiveOrZeroInteger);
        getChapterItemsRequest.getActivityCategoryList().stream().forEach(FormatValidator::validatePositiveOrZeroInteger);

        return ResponseEntity.status(OK).body(itemImageApiClient.getChapterItemImages(getChapterItemsRequest));
    }

    @PostMapping("/chapters/adjust")
    @ApiOperation(
            value = GET_ADJUSTED_CHAPTER_ITEM_IMAGES_FROM_TSHERPA,
            notes = GET_ADJUSTED_CHAPTER_ITEM_IMAGES_FROM_TSHERPA_DESCRIPTION
    )
    public ResponseEntity<GetItemImagesResponse> getAdjustedChapterItemImagesFromTsherpa(
            @ApiParam(value = GET_CHAPTER_ITEM_IMAGES_FROM_TSHERPA_FORM)
            @RequestBody GetChapterItemsRequest getChapterItemsRequest
    ) {
        getChapterItemsRequest.getLevelCnt().stream().forEach(FormatValidator::validatePositiveOrZeroInteger);
        getChapterItemsRequest.getActivityCategoryList().stream().forEach(FormatValidator::validatePositiveOrZeroInteger);
        GetItemImagesResponse getItemImagesResponse = itemImageApiClient.getChapterItemImages(getChapterItemsRequest);
        getItemImagesResponse.adjustData(getChapterItemsRequest);

        return ResponseEntity.status(OK).body(getItemImagesResponse);
    }

    @GetMapping("/exam")
    @ApiOperation(value = GET_EXAM_ITEM_IMAGES_FROM_TSHERPA, notes = GET_EXAM_ITEM_IMAGES_FROM_TSHERPA_DESCRIPTION)
    public ResponseEntity<GetItemImagesResponse> getExamItemImagesFromTsherpa(
            @ApiParam(value = TSHERPA_EXAM_ID, example = TSHERPA_EXAM_ID_EXAMPLE)
            @RequestParam String examId
    ) {
        FormatValidator.validatePositiveOrZeroInteger(examId);

        return ResponseEntity.status(OK).body(itemImageApiClient.getExamItemImages(examId));
    }

    @GetMapping("/similar-items")
    @ApiOperation(
            value = GET_SIMILAR_ITEM_IMAGES_FROM_TSHERPA,
            notes = GET_SIMILAR_ITEM_IMAGES_FROM_TSHERPA_DESCRIPTION
    )
    public ResponseEntity<GetItemImagesResponse> getSimilarItemImagesFromTsherpa(
            @ApiParam(value = TSHERPA_ITEM_IDS, defaultValue = TSHERPA_ITEM_IDS_EXAMPLE)
            @RequestParam List<String> itemIds,
            @ApiParam(value = TSHERPA_EXCLUDED_ITEM_IDS, defaultValue = TSHERPA_EXCLUDED_ITEM_IDS_EXAMPLE)
            @RequestParam List<String> excludedItemIds
    ) {
        itemIds.stream().forEach(FormatValidator::validatePositiveOrZeroInteger);
        excludedItemIds.stream().forEach(FormatValidator::validatePositiveOrZeroInteger);

        return ResponseEntity.status(OK).body(
                itemImageApiClient.getSimilarItemImages(GetSimilarItemsRequest.of(itemIds, excludedItemIds))
        );
    }
}
