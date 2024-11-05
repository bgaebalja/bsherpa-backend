package bgaebalja.bsherpa.exam.controller;

import bgaebalja.bsherpa.exam.domain.Exam;
import bgaebalja.bsherpa.exam.domain.GetExamsResponse;
import bgaebalja.bsherpa.exam.domain.RegisterExamRequest;
import bgaebalja.bsherpa.exam.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

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

    @GetMapping()
    public ResponseEntity<GetExamsResponse> getExams() {
        List<Exam> exams = examService.getBsherpaExams();

        return ResponseEntity.status(OK).body(GetExamsResponse.from(exams));
    }
}
