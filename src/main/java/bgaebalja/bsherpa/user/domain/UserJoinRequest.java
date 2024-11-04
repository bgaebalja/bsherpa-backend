package bgaebalja.bsherpa.user.domain;

import bgaebalja.bsherpa.validation.CustomValidField;
import bgaebalja.bsherpa.validation.CustomValidation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserJoinRequest extends CustomValidation {

  @CustomValidField(jsonKeyName = "email")
  private String email;

  @CustomValidField(jsonKeyName = "password")
  private String password;

  @CustomValidField(jsonKeyName = "username")
  private String username;

  private String role;

  private String clazz;
  private String grade;

}