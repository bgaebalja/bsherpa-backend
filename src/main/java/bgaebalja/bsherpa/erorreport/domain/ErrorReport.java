package bgaebalja.bsherpa.erorreport.domain;

import bgaebalja.bsherpa.audit.BaseGeneralEntity;
import bgaebalja.bsherpa.user.domain.Users;
import bgaebalja.bsherpa.util.FormatConverter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class ErrorReport extends BaseGeneralEntity {
    @Column(nullable = false)
    private Long itemId;

    @Column(nullable = false, length = 20)
    private String type;

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "reporter_id")
    private Users reporter;

    private ErrorReport(Long itemId, String type, String content, Users reporter) {
        this.itemId = itemId;
        this.type = type;
        this.content = content;
        this.reporter = reporter;
    }

    public static ErrorReport from(RegisterErrorReportRequest registerErrorReportRequest, Users reporter) {
        return new ErrorReport(
                FormatConverter.parseToLong(registerErrorReportRequest.getItemId()),
                registerErrorReportRequest.getType(),
                registerErrorReportRequest.getContent(),
                reporter
        );
    }
}
