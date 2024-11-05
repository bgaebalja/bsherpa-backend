package bgaebalja.bsherpa.user.controller;

import static bgaebalja.bsherpa.validation.Valid.JOIN;
import static org.springframework.http.HttpStatus.CREATED;

import bgaebalja.bsherpa.user.domain.UserJoinRequest;
import bgaebalja.bsherpa.user.dto.request.UserSwaggerLoginRequest;
import bgaebalja.bsherpa.user.dto.response.UserSwaggerLoginResponse;
import bgaebalja.bsherpa.user.service.UserService;
import bgaebalja.bsherpa.validation.CustomValid;
import java.util.Map;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private static final String SIGN_IN = "로그인";
    private static final String SIGN_IN_DESCRIPTION = "이메일 주소와 비밀번호를 입력해 로그인을 할 수 있습니다.";
    private static final String SIGN_IN_FORM = "로그인 양식";

  @CustomValid(schema = JOIN)
  @PostMapping("/join")
  public ResponseEntity<Map<String, String>> postJoin(@RequestBody UserJoinRequest userJoinRequest) {
      log.info("POST join request: {}", userJoinRequest);
      if (userJoinRequest.getRole().equals("STUDENT") && !userJoinRequest.getClazz().isEmpty() && !userJoinRequest.getGrade().isEmpty()) {
        userService.saveStudent(userJoinRequest);
      return ResponseEntity.status(CREATED).body(Map.of("SUCCESS","JOIN"));
      }
      userService.saveUser(userJoinRequest);
      return ResponseEntity.status(CREATED).body(Map.of("SUCCESS","JOIN"));
  }

    @ApiOperation(value = SIGN_IN, notes = SIGN_IN_DESCRIPTION)
  @PostMapping("/login/swagger")
  public ResponseEntity<UserSwaggerLoginResponse> postLoginSwagger(@RequestBody @ApiParam(value = SIGN_IN_FORM) UserSwaggerLoginRequest userSwaggerLoginRequest){
    UserSwaggerLoginResponse response = userService.getUser(userSwaggerLoginRequest);
    return ResponseEntity.status(CREATED).body(response);
  }

}