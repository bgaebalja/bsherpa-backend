package bgaebalja.bsherpa.question.controller;

import bgaebalja.bsherpa.client.item.GetItemsRequest;
import bgaebalja.bsherpa.client.item.GetItemsResponse;
import bgaebalja.bsherpa.client.item.ItemApiClient;
import bgaebalja.bsherpa.question.domain.CreateQuestionRequest;
import bgaebalja.bsherpa.question.service.QuestionService;
import bgaebalja.bsherpa.util.ResponseGenerator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final ItemApiClient itemApiClient;
    private final QuestionService questionService;

    private static final String GET_ITEMS_FROM_TSHERPA = "T셀파의 문제 목록 조회";
    private static final String GET_ITEMS_FROM_TSHERPA_DESCRIPTION
            = "문제 ID 목록을 입력해 T셀파의 문제 목록을 조회할 수 있습니다.";
    private static final String TSHERPA_ITEM_IDS = "T셀파의 문제 ID 목록";
    private static final String TSHERPA_ITEM_IDS_EXAMPLE = "494519, 494520";

    private static final String REGISTER_QUESTION = "문제 등록";
    private static final String REGISTER_QUESTION_DESCRIPTION = "문제 ID를 입력해 문제를 등록할 수 있습니다.";
    private static final String REGISTER_QUESTION_FORM = "문제 등록 양식";

    @GetMapping()
    @ApiOperation(value = GET_ITEMS_FROM_TSHERPA, notes = GET_ITEMS_FROM_TSHERPA_DESCRIPTION)
    public ResponseEntity<GetItemsResponse> getItemsFromTsherpa(
            @ApiParam(value = TSHERPA_ITEM_IDS, defaultValue = TSHERPA_ITEM_IDS_EXAMPLE)
            @RequestParam List<String> itemIds
    ) {
        return ResponseEntity.status(OK).body(itemApiClient.getItems(GetItemsRequest.from(itemIds)));
    }

    @PostMapping()
    @ApiOperation(value = REGISTER_QUESTION, notes = REGISTER_QUESTION_DESCRIPTION)
    public ResponseEntity<Void> registerQuestion(
            @ApiParam(value = REGISTER_QUESTION_FORM) @RequestBody CreateQuestionRequest createQuestionRequest
    ) {
        return ResponseGenerator.buildResponse(questionService.createQuestion(createQuestionRequest));
    }
}
