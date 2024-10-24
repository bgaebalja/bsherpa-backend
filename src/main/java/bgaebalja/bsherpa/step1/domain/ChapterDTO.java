package bgaebalja.bsherpa.step1.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChapterDTO {
  private String curriculumCode;
  private String curriculumName;
  private Long subjectId;
  private String subjectName;
  private Long largeChapterId;
  private String largeChapterName;
  private Long mediumChapterId;
  private String mediumChapterName;
  private Long smallChapterId;
  private String smallChapterName;
  private Long topicChapterId;
  private String topicChapterName;
}
