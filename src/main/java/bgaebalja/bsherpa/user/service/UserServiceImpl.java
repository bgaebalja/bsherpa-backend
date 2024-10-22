package bgaebalja.bsherpa.user.service;

import bgaebalja.bsherpa.user.domain.UserJoinRequest;
import bgaebalja.bsherpa.user.domain.Users;
import bgaebalja.bsherpa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void saveUser(UserJoinRequest userJoinRequest) {
    String password = passwordEncoder.encode(userJoinRequest.getPassword());
    Users user = Users.createUser(userJoinRequest.getUsername(), password,
        userJoinRequest.getEmail());
    userRepository.save(user);
  }
}
