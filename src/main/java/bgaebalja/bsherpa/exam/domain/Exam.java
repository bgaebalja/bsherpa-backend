package bgaebalja.bsherpa.exam.domain;

import bgaebalja.bsherpa.audit.BaseGeneralEntity;
import bgaebalja.bsherpa.book.domain.Book;
import bgaebalja.bsherpa.collection.domain.Collection;
import bgaebalja.bsherpa.user.domain.Users;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Exam extends BaseGeneralEntity {
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "exam_name")
    private String examName;

    @Column(name = "total_count")
    private Long totalCount;

    @Column(name = "open_yn")
    private Boolean openYn;

    @Column(name = "exam_type")
    private ExamType examType = ExamType.ALL;

    @OneToMany(mappedBy = "exam", cascade = PERSIST)
    private List<Collection> collections;

    @Builder
    private Exam(Users user, Book book, String examName, Long totalCount, Boolean openYn, List<Collection> collections) {
        this.user = user;
        this.book = book;
        this.examName = examName;
        this.totalCount = totalCount;
        this.openYn = openYn;
        this.collections = collections != null ? collections : new ArrayList<>();
    }

    public void addCollection(Collection collection) {
        collections.add(collection);
        collection.assignExam(this);
    }

    public static Exam from(Users user, Book book, RegisterExamRequest registerExamRequest) {
        Exam exam = Exam.builder()
                .user(user)
                .book(book)
                .examName(registerExamRequest.getExamName())
                .totalCount(registerExamRequest.getTotalCount())
                .openYn(true)
                .collections(new ArrayList<>())
                .build();

        registerExamRequest.getCollections().forEach(registerCollectionRequest -> {
            Collection collection = registerCollectionRequest.toEntity();
            exam.addCollection(collection); // addCollection 호출로 양방향 관계 설정
        });

        return exam;
    }

}
