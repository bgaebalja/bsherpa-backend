package bgaebalja.bsherpa.security.handler;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    Gson gson = new Gson();
    String jsonStr = gson.toJson(Map.of("error","ERROR_LOGIN"));
    response.setContentType(APPLICATION_JSON_VALUE);
    PrintWriter out = response.getWriter();
    out.write(jsonStr);
    out.flush();
    out.close();
  }
}
