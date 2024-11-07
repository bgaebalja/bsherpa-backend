package bgaebalja.bsherpa.exam.controller;

import bgaebalja.bsherpa.exam.domain.Exam;
import bgaebalja.bsherpa.exam.domain.GetExamResponse;
import bgaebalja.bsherpa.exam.domain.GetExamsResponse;
import bgaebalja.bsherpa.exam.domain.RegisterExamRequest;
import bgaebalja.bsherpa.exam.service.ExamService;
import bgaebalja.bsherpa.util.FormatConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("exams")
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
    public ResponseEntity<GetExamsResponse> getExams(
            @RequestParam(value = "email", defaultValue = "") String email,
            @RequestParam String subjectName
    ) {
        List<Exam> exams = examService.getExams(email, subjectName);

        return ResponseEntity.status(OK).body(GetExamsResponse.from(exams));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetExamResponse> getExam(@PathVariable("id") String id) {
        Exam exam = examService.getExam(FormatConverter.parseToLong(id));

        return ResponseEntity.status(OK).body(GetExamResponse.from(exam));
    }

    @GetMapping("/storage")
    public ResponseEntity<GetExamsResponse> getExamByEmail(@RequestParam("email") String email) {
        List<Exam> userExams = examService.getExamByUser(email);

        return ResponseEntity.status(OK).body(GetExamsResponse.from(userExams));
    }
}
