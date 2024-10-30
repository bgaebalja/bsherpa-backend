package bgaebalja.bsherpa.file.domain;

import bgaebalja.bsherpa.audit.BaseGeneralEntity;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class UserFile extends BaseGeneralEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TargetType targetType;

    @Column(nullable = false)
    private Long targetId;

    @Column(name = "s3_url", nullable = false, length = 500)
    private String s3Url;

    private UserFile(TargetType targetType, Long targetId, String s3Url) {
        this.targetType = targetType;
        this.targetId = targetId;
        this.s3Url = s3Url;
    }

    public static UserFile of(TargetType targetType, Long targetId, String s3Url) {
        return new UserFile(targetType, targetId, s3Url);
    }

    public String getS3Url() {
        return s3Url;
    }
}
