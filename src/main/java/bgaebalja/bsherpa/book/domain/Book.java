package bgaebalja.bsherpa.book.domain;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import bgaebalja.bsherpa.audit.BaseGeneralEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
  private String bookName;

  @Column(nullable = false)
  private String subjectId;


}
