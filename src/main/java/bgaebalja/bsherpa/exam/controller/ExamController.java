package bgaebalja.bsherpa.exam.controller;

import bgaebalja.bsherpa.exam.domain.RegisterExamRequest;
import bgaebalja.bsherpa.exam.repository.ExamRepository;
import bgaebalja.bsherpa.exam.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exam")
@RequiredArgsConstructor
public class ExamController {
    private static ExamService examService;

    @PostMapping("/step03/register")
    public ResponseEntity<Void> registerExam(
            @RequestBody RegisterExamRequest registerExamRequest) {
        System.out.println("getId : "+registerExamRequest.getEmail());
        System.out.println("getName : "+registerExamRequest.getExamName());
        System.out.println("getCollection : "+registerExamRequest.getCollections().get(0).getPassages().get(0));

        //boolean successYn = examService.registerExam(registerExamRequest);

        //return successYn ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }
}
