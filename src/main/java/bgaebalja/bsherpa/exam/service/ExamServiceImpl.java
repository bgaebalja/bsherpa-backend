package bgaebalja.bsherpa.exam.service;

import bgaebalja.bsherpa.book.domain.Book;
import bgaebalja.bsherpa.book.repository.BookRepository;
import bgaebalja.bsherpa.exam.domain.Exam;
import bgaebalja.bsherpa.exam.domain.RegisterExamRequest;
import bgaebalja.bsherpa.exam.exception.ExamNotFoundException;
import bgaebalja.bsherpa.exam.repository.ExamRepository;
import bgaebalja.bsherpa.exception.UserNotFoundException;
import bgaebalja.bsherpa.subject.domain.Subject;
import bgaebalja.bsherpa.subject.repository.SubjectRepository;
import bgaebalja.bsherpa.user.domain.Users;
import bgaebalja.bsherpa.user.repository.UserRepository;
import bgaebalja.bsherpa.util.FormatValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static bgaebalja.bsherpa.exam.exception.ExceptionMessage.EXAM_NOT_FOUND_EXCEPTION_MESSAGE;
import static bgaebalja.bsherpa.exception.ExceptionMessage.USER_NOT_FOUND_EXCEPTION_MESSAGE;
import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
@RequiredArgsConstructor
@Transactional(isolation = READ_COMMITTED, readOnly = true, timeout = 20)
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final SubjectRepository subjectRepository;

    @Override
    @Transactional
    public boolean registerExam(RegisterExamRequest registerExamRequest) {
        Users user = userRepository.findByUserId(registerExamRequest.getEmail()).get();
        Book book = bookRepository.findByBookId(registerExamRequest.getBookId()).orElseThrow(() -> new NullPointerException("Book not found"));

        Exam createExam = Exam.from(user, book, registerExamRequest);
        try {
            examRepository.save(createExam);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Exam> getExams(String email, String subjectName) {
        if (!FormatValidator.hasValue(subjectName)) {
            return getExams(email);
        }

        Users user = userRepository.findByUserId(email)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_EXCEPTION_MESSAGE, email)));
        List<Subject> subjects = subjectRepository.findByName(subjectName);
        List<Book> books = new ArrayList<>();
        for (Subject subject : subjects) {
            List<Book> foundBooks = bookRepository.findBySubjectIdAndDeleteYnFalse(subject.getId());
            for (Book book : foundBooks) {
                books.add(book);
            }
        }
        List<Exam> exams = new ArrayList<>();
        for (Book book : books) {
            List<Exam> foundExams = examRepository.findByUserIdAndBookIdAndDeleteYnFalse(user.getId(), book.getId());
            for (Exam exam : foundExams) {
                exams.add(exam);
            }
        }
        return exams;
    }

    private List<Exam> getExams(String email) {
        if (FormatValidator.hasValue(email)) {
            Users user = userRepository.findByUserId(email).orElseThrow(
                    () -> new UserNotFoundException(String.format(USER_NOT_FOUND_EXCEPTION_MESSAGE, email))
            );
            return examRepository.findByUserIdAndDeleteYnFalseAndOpenYnTrue(user.getId());
        }

        return examRepository.findByDeleteYnFalseAndOpenYnTrue();
    }

    @Override
    public Exam getExam(Long examId) {
        return examRepository.findByIdAndDeleteYnFalse(examId)
                .orElseThrow(() -> new ExamNotFoundException(String.format(EXAM_NOT_FOUND_EXCEPTION_MESSAGE, examId)));
    }

    @Override
    public List<Exam> getExamByUser(String email) {
        Long userId = userRepository.findByUserId(email).get().getId();

        return examRepository.findByUserIdAndDeleteYnFalseOrderByCreatedAtDesc(userId);
    }
}
