package bgaebalja.bsherpa.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserJoinRequest {

  private String email;
  private String password;
  private String username;

}