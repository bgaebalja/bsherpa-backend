package bgaebalja.bsherpa.question.controller;

import bgaebalja.bsherpa.question.domain.CreateQuestionRequest;
import bgaebalja.bsherpa.question.service.QuestionService;
import bgaebalja.bsherpa.util.ResponseGenerator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    private static final String REGISTER_QUESTION = "문제 등록";
    private static final String REGISTER_QUESTION_DESCRIPTION = "문제 ID를 입력해 문제를 등록할 수 있습니다.";
    private static final String REGISTER_QUESTION_FORM = "문제 등록 양식";

    @PostMapping()
    @ApiOperation(value = REGISTER_QUESTION, notes = REGISTER_QUESTION_DESCRIPTION)
    public ResponseEntity<Void> registerQuestion(
            @ApiParam(value = REGISTER_QUESTION_FORM) @RequestBody CreateQuestionRequest createQuestionRequest
    ) {
        return ResponseGenerator.buildResponse(questionService.createQuestion(createQuestionRequest));
    }
}
