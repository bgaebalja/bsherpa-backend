package bgaebalja.bsherpa.client.chapter;

import lombok.Getter;

@Getter
public class GetBookInformationResponse {
    private Long subjectId;
    private String subjectName;
    private String curriculumCode;
    private String curriculumName;
    private String schoolLevelCode;
    private String schoolLevelName;
    private String gradeCode;
    private String gradeName;
    private String termCode;
    private String termName;
    private String areaCode;
    private String areaName;
}
