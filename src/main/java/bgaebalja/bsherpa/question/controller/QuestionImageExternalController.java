package bgaebalja.bsherpa.question.controller;

import bgaebalja.bsherpa.client.item.GetItemsRequest;
import bgaebalja.bsherpa.client.itemimage.GetItemImagesResponse;
import bgaebalja.bsherpa.client.itemimage.ItemImageApiClient;
import bgaebalja.bsherpa.util.FormatValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping()
    @ApiOperation(value = GET_ITEM_IMAGES_FROM_TSHERPA, notes = GET_ITEM_IMAGES_FROM_TSHERPA_DESCRIPTION)
    public ResponseEntity<GetItemImagesResponse> getItemImagesFromTsherpa(
            @ApiParam(value = TSHERPA_ITEM_IDS, defaultValue = TSHERPA_ITEM_IDS_EXAMPLE)
            @RequestParam List<String> itemIds
    ) {
        itemIds.stream().forEach(FormatValidator::validatePositiveInteger);

        return ResponseEntity.status(OK).body(itemImageApiClient.getItemImages(GetItemsRequest.of(itemIds)));
    }
}
