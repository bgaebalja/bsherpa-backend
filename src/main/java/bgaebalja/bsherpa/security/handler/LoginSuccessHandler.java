package bgaebalja.bsherpa.security.handler;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import bgaebalja.bsherpa.user.domain.UserDTO;
import bgaebalja.bsherpa.util.FormatConverter;
import bgaebalja.bsherpa.util.JwtUtil;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

  private final JwtUtil jwtUtil;
  private final Environment env;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    int accessTokenExpirationTime = FormatConverter.parseToInt(env.getProperty("jwt.access-token.expiration-time"));
    int refreshTokenExpirationTime = FormatConverter.parseToInt(env.getProperty("jwt.refresh-token.expiration-time"));

    UserDTO userDTO = (UserDTO) authentication.getPrincipal();
    Map<String, Object> claims = userDTO.getClaims();
    String accessToken = jwtUtil.generateToken(claims, accessTokenExpirationTime);
    String refreshToken = jwtUtil.generateToken(claims, refreshTokenExpirationTime);
    claims.put("accessToken", accessToken);
    claims.put("refreshToken", refreshToken);
    Gson gson = new Gson();
    String jsonStr = gson.toJson(claims);
    response.setContentType(APPLICATION_JSON_VALUE);
    PrintWriter out = response.getWriter();
    out.write(jsonStr);
    out.flush();
    out.close();
  }
}
