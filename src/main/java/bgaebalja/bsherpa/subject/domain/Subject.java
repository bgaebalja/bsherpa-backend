package bgaebalja.bsherpa.subject.domain;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import bgaebalja.bsherpa.audit.BaseEntity;
import bgaebalja.bsherpa.clazz.domain.Clazz;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class Subject extends BaseEntity {

  @Id @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private String name;

  private String code;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "class_id")
  private Clazz clazz;

}
