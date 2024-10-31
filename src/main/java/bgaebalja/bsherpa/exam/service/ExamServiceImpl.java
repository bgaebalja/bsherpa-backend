package bgaebalja.bsherpa.exam.service;

import bgaebalja.bsherpa.book.domain.Book;
import bgaebalja.bsherpa.book.repository.BookRepository;
import bgaebalja.bsherpa.exam.domain.Exam;
import bgaebalja.bsherpa.exam.domain.RegisterExamRequest;
import bgaebalja.bsherpa.exam.repository.ExamRepository;
import bgaebalja.bsherpa.user.domain.Users;
import bgaebalja.bsherpa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public boolean registerExam(RegisterExamRequest registerExamRequest) {
        Users user = userRepository.findByUserId(registerExamRequest.getEmail()).get();
        Book book = bookRepository.findById(registerExamRequest.getBookId()).get();

        System.out.println("user check: "+user.getUsername());
        System.out.println("book check: "+book.getName());
        System.out.println("DTO check: "+registerExamRequest.getExamName());

        Exam createExam = Exam.from(user, book, registerExamRequest);

        try {
            examRepository.save(createExam);
            return true;
        }catch (Exception e) { return false; }
    }

}
