package bgaebalja.bsherpa.user.controller;

import bgaebalja.bsherpa.exception.JwtCustomException;
import bgaebalja.bsherpa.util.FormatConverter;
import bgaebalja.bsherpa.util.JwtUtil;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
@Slf4j
public class RefreshController {

  private final JwtUtil jwtUtil;
  private final Environment env;

  @RequestMapping("/refresh")
  public Map<String,Object> refresh(
      @RequestHeader("Authorization") String authHeader,
      String refreshToken
  ){

    int accessTokenExpirationTime = FormatConverter.parseToInt(env.getProperty("jwt.access-token.expiration-time"));

    if(refreshToken == null){throw new JwtCustomException("NULL_REFRESH");}
    if(authHeader == null || authHeader.length() < 7){throw new JwtCustomException("INVALID STRING");}

    String accessToken = authHeader.substring(7);
    if(!checkExpiredToken(accessToken)){return Map.of("accessToken",accessToken,"refreshToken",refreshToken);}

    Map<String, Object> claims = jwtUtil.validateToken(refreshToken);
    log.info("refresh ... claims: {}", claims);
    String newAccessToken = jwtUtil.generateToken(claims, accessTokenExpirationTime);
    String newRefreshToken = checkTime((Integer) claims.get("exp")) ?
        jwtUtil.generateToken(claims, 60 * 24) : refreshToken;
    return Map.of("accessToken",newAccessToken,"refreshToken",newRefreshToken);
  }

  private boolean checkTime(Integer exp){
    java.util.Date expDate = new java.util.Date((long)exp * 1000);
    long gap = expDate.getTime() - System.currentTimeMillis();
    long leftMin = gap / (60 * 1000);
    return leftMin < 60;
  }

  private boolean checkExpiredToken(String token){
    try {
      jwtUtil.validateToken(token);
    }catch (JwtCustomException ex){
      if (ex.getMessage().equals("Expired")) {
        return true;
      }
    }
    return false;
  }
}
