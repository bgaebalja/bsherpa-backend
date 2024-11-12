package bgaebalja.bsherpa.user.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserSwaggerLoginRequest {

  private static final String EMAIL_OR_USERNAME_VALUE = "이메일 주소 또는 사용자 이름(아이디)";
  public static final String EMAIL_EXAMPLE = "tane9537@nate.com";

  public static final String PASSWORD_VALUE = "비밀번호";
  public static final String PASSWORD_EXAMPLE = "qwe123";

  @ApiModelProperty(value = EMAIL_OR_USERNAME_VALUE, example = EMAIL_EXAMPLE)
  private String email;

  @ApiModelProperty(value = PASSWORD_VALUE, example = PASSWORD_EXAMPLE)
  private String password;

}
