package bgaebalja.bsherpa.file.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TargetType {
    EXAM_FILE("시험지 PDF 파일"),
    ERROR_REPORT("오류 신고 이미지");

    private final String description;
}
