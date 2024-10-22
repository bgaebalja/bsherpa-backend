package bgaebalja.bsherpa.user.controller;

import static org.springframework.http.HttpStatus.*;

import bgaebalja.bsherpa.user.domain.UserJoinRequest;
import bgaebalja.bsherpa.user.service.UserService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @PostMapping("/join")
  public ResponseEntity<Map<String, String>> postJoin(@RequestBody UserJoinRequest userJoinRequest) {
      userService.saveUser(userJoinRequest);
      return ResponseEntity.status(CREATED).body(Map.of("SUCCESS","JOIN"));
  }

}