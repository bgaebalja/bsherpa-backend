package bgaebalja.bsherpa.book.domain;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import bgaebalja.bsherpa.audit.BaseGeneralEntity;
import bgaebalja.bsherpa.subject.domain.Subject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = PROTECTED)
public class Book extends BaseGeneralEntity {

  @Id @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String bookId;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String author;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "subject_id")
  private Subject subject;


}
