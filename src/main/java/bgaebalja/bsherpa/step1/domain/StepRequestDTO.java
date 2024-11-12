package bgaebalja.bsherpa.step1.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StepRequestDTO {
  private String subjectId;  // int에서 String으로 변경
}