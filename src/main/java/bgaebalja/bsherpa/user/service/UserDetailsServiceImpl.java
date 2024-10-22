package bgaebalja.bsherpa.user.service;

import bgaebalja.bsherpa.user.domain.UserDTO;
import bgaebalja.bsherpa.user.domain.UserRole;
import bgaebalja.bsherpa.user.domain.Users;
import bgaebalja.bsherpa.user.repository.UserRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Users loginUser = userRepository.findByEmail(email);
    if (loginUser == null) {
      throw new UsernameNotFoundException(email);
    }
    return new UserDTO(
        loginUser.getEmail(),
        loginUser.getPassword(),
        loginUser.getUsername(),
        loginUser.getRoles().stream().map(UserRole::getRole).collect(Collectors.toList())
    );
  }
}
