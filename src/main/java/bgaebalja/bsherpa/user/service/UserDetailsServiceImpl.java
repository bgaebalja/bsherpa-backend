package bgaebalja.bsherpa.user.service;

import bgaebalja.bsherpa.user.domain.UserDTO;
import bgaebalja.bsherpa.user.domain.UserRole;
import bgaebalja.bsherpa.user.domain.Users;
import bgaebalja.bsherpa.user.repository.UserRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    log.info("loadUserByUsername {}",email);
    Users loginUser = userRepository.getUserWithRoles(email);
    if (loginUser == null) {
      throw new UsernameNotFoundException(email);
    }
    return new UserDTO(
        loginUser.getUserId(),
        loginUser.getPassword(),
        loginUser.getUsername(),
        loginUser.getClazz(),
        loginUser.getGrade(),
        loginUser.getRoles().stream().map(UserRole::getRole).collect(Collectors.toList())
    );
  }
}
