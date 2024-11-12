package bgaebalja.bsherpa.security.handler;

import static org.springframework.http.HttpStatus.FORBIDDEN;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {


  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    Gson gson = new Gson();
    String result = gson.toJson(Map.of("error","ERROR_ACCESSDENIED"));
    response.setStatus(FORBIDDEN.value()); //403
    PrintWriter printWriter = response.getWriter();
    printWriter.print(result);
    printWriter.flush();
    printWriter.close();
  }
}
