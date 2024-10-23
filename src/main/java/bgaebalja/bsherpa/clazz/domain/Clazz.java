package bgaebalja.bsherpa.clazz.domain;

import static javax.persistence.GenerationType.IDENTITY;

import bgaebalja.bsherpa.audit.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "class")
public class Clazz extends BaseEntity {

  @Id @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String name;

}
