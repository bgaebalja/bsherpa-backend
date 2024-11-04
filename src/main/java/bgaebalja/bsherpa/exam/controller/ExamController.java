package bgaebalja.bsherpa.exam.controller;

import bgaebalja.bsherpa.exam.domain.RegisterExamRequest;
import bgaebalja.bsherpa.exam.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("exam")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;

    @PostMapping("/step03/register")
    public ResponseEntity<Void> registerExam(
            @Valid @RequestBody RegisterExamRequest registerExamRequest) {
        boolean successYn = examService.registerExam(registerExamRequest);

        return successYn ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
