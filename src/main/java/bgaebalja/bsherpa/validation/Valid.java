package bgaebalja.bsherpa.validation;

import lombok.Getter;

public enum Valid {

  JOIN("join", "회원가입 유효성 검증");
  @Getter
  private final String name;
  private final String description;

  Valid(String name, String description) {
    this.name = name;
    this.description = description;
  }


}
