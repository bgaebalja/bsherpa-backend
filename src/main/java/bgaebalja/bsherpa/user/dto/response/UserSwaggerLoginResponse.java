package bgaebalja.bsherpa.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class UserSwaggerLoginResponse {

  private String email;
  private String accessToken;
  private String refreshToken;

}
