package bgaebalja.bsherpa.passage.domain;

import bgaebalja.bsherpa.audit.BaseGeneralEntity;
import bgaebalja.bsherpa.collection.domain.Collection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Passage extends BaseGeneralEntity {
    @Column(nullable = false)
    private Long passageId;

    @Column(nullable = false)
    private String html;

    @Column(nullable = false)
    private String url;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @Builder
    private Passage(Long passageId,String html, String url, Collection collection) {
        this.passageId = passageId;
        this.html = html;
        this.url = url;
        this.collection = collection;
    }

    //DTO 만들기 전이라 보류
    public static Passage from(){
        return Passage.builder()
                .build();
    }
}
