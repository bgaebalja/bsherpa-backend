package bgaebalja.bsherpa.option.domain;

import bgaebalja.bsherpa.audit.BaseGeneralEntity;
import bgaebalja.bsherpa.question.domain.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Table(name = "options")
@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Option extends BaseGeneralEntity {
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "option_no")
    private String optionNo;

    private String html;

    @Builder
    private Option(Question question, String optionNo, String html) {
        this.question = question;
        this.optionNo = optionNo;
        this.html = html;
    }

    public void assignQuestion(Question question) {
        this.question = question;
    }

}
