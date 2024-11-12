package bgaebalja.bsherpa.exam.service;

import bgaebalja.bsherpa.exam.domain.Exam;
import bgaebalja.bsherpa.exam.domain.RegisterExamRequest;

import java.util.List;

public interface ExamService {
    boolean registerExam(RegisterExamRequest registerExamRequest);

    List<Exam> getExams(String email, String subjectName);

    Exam getExam(Long examId);

    List<Exam> getExamByUser(String email);
}
