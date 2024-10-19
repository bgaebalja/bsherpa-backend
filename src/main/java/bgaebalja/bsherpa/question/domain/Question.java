package bgaebalja.bsherpa.question.domain;

import bgaebalja.bsherpa.audit.BaseGeneralEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Question extends BaseGeneralEntity {
}
