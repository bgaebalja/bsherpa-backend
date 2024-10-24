package bgaebalja.bsherpa.step1.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Step1ResponseDTO {
  private String successYn;
  private List<ChapterDTO> chapterList;
}