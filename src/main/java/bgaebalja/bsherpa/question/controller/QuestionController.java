package bgaebalja.bsherpa.question.controller;

import bgaebalja.bsherpa.client.item.GetItemsRequest;
import bgaebalja.bsherpa.client.item.GetItemsResponse;
import bgaebalja.bsherpa.client.item.ItemApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final ItemApiClient itemApiClient;

    @GetMapping()
    public ResponseEntity<GetItemsResponse> getItemsFromTsherpa(@RequestParam List<String> itemIds) {
        return ResponseEntity.status(OK).body(itemApiClient.getItems(GetItemsRequest.from(itemIds)));
    }
}
