package bgaebalja.bsherpa.collection.domain;

import bgaebalja.bsherpa.audit.BaseGeneralEntity;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Collection extends BaseGeneralEntity {
}
