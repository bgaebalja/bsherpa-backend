package bgaebalja.bsherpa.audit;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import static bgaebalja.bsherpa.util.EntityConstant.BOOLEAN_DEFAULT_FALSE;
import static javax.persistence.GenerationType.IDENTITY;


@EntityListeners(value = AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class BaseGeneralEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = BOOLEAN_DEFAULT_FALSE)
    private boolean deleteYn;

    protected void deleteEntity() {
        deleteYn = true;
    }

    protected void undeleteEntity() {
        deleteYn = false;
    }
}
