package bgaebalja.bsherpa.security.filter;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import bgaebalja.bsherpa.user.domain.UserDTO;
import bgaebalja.bsherpa.util.JwtUtil;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtCheckFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    //true 체크 안 한다
    String path = request.getRequestURI();
    if (path.startsWith("/users/")) {
      return true;
    }
    if (path.startsWith("/books")) {
      return true;
    }
    //false 체크한다
    return false;
  }


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String authHeaderStr = request.getHeader("Authorization");

    try {
      String accessToken = authHeaderStr.substring(7);
      Map<String, Object> claims = jwtUtil.validateToken(accessToken);


      String email = (String) claims.get("email");
      String password = (String) claims.get("password");
      String username = (String) claims.get("username");
      List<String> roles = (List<String>) claims.get("roles");
      UserDTO memberDTO = new UserDTO(email, password, username, roles);

      //Spring security 가 사용하는 토큰
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
          memberDTO, password, memberDTO.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);

      filterChain.doFilter(request, response);

    } catch (Exception e) {

      log.error("JWT Check Error", e);
      Gson gson = new Gson();
      String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));
      response.setContentType(APPLICATION_JSON_VALUE);
      response.getWriter().write(msg);
    }
  }
}
