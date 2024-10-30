package bgaebalja.bsherpa.user.controller;

import bgaebalja.bsherpa.user.domain.UserDTO;
import bgaebalja.bsherpa.user.service.UserService;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class SocialController {

  private final static String ERROR_KEY = "error";
  private final UserService userService;

  @GetMapping("/kakao")
  public ResponseEntity<?> getKakaoSocial(String accessToken) {
    Object kakaoUser = userService.getKakaoUser(accessToken);
    if (kakaoUser instanceof UserDTO) {
      return ResponseEntity.ok(((UserDTO) kakaoUser).getClaims());
    }
    return ResponseEntity.ok(Map.of(ERROR_KEY, Objects.requireNonNull(kakaoUser)));
  }

}
