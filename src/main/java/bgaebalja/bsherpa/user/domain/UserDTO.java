package bgaebalja.bsherpa.user.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDTO extends User {

  private String email, password, username;

  private List<String> roles;

  public UserDTO(String email, String password, String username, List<String> roles) {
    super(
        email,
        password,
        roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
            .collect(Collectors.toList())
    );
    this.email = email;
    this.password = password;
    this.username = username;
    this.roles = roles;
  }

  public Map<String,Object> getClaims() {
    Map<String, Object> claims = new HashMap<>();
    claims.put("email", email);
    claims.put("password", password);
    claims.put("username", username);
    claims.put("roles", roles);
    return claims;
  }

}
