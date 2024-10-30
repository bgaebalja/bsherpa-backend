package bgaebalja.bsherpa.erorreport.domain;

import bgaebalja.bsherpa.audit.BaseGeneralEntity;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class ErrorReport extends BaseGeneralEntity {
    @Column(nullable = false, length = 20)
    private String type;

    @Column(nullable = false, length = 200)
    private String content;

    private ErrorReport(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public static ErrorReport from(RegisterErrorReportRequest registerErrorReportRequest) {
        return new ErrorReport(registerErrorReportRequest.getType(), registerErrorReportRequest.getContent());
    }
}
