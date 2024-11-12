package bgaebalja.bsherpa.util;

import static java.nio.charset.StandardCharsets.UTF_8;

import bgaebalja.bsherpa.exception.JwtCustomException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

  private final Environment env;

  @Value("${jwt.secret.key}")
  private String key;

  public  String generateToken(Map<String, Object> valueMap, int min) {
    SecretKey key = null;
    try{
      key = Keys.hmacShaKeyFor(this.key.getBytes(UTF_8));
    }catch(Exception e){
      throw new RuntimeException(e.getMessage());
    }
    String jwtStr = Jwts.builder()
        .setHeader(Map.of("typ"
            ,"JWT"))
        .setClaims(valueMap)
        .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
        .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
        .signWith(key)
        .compact();
    return jwtStr;
  }

  public Map<String, Object> validateToken(String token) {
    Map<String, Object> claim = null;
    try{
      SecretKey key = Keys.hmacShaKeyFor(this.key.getBytes(UTF_8));
      claim = Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(token) // 파싱 및 검증, 실패 시 에러
          .getBody();
    }catch(MalformedJwtException malformedJwtException){
      throw new JwtCustomException("MalFormed");
    }catch(ExpiredJwtException expiredJwtException){
      throw new JwtCustomException("Expired");
    }catch(InvalidClaimException invalidClaimException){
      throw new JwtCustomException("Invalid");
    }catch(JwtException jwtException){
      throw new JwtCustomException("JWTError");
    }catch(Exception e){
      throw new JwtCustomException("Error");
    }
    return claim;
  }
}
