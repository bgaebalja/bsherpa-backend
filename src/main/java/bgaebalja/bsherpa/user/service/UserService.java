package bgaebalja.bsherpa.user.service;

import bgaebalja.bsherpa.user.domain.UserDTO;
import bgaebalja.bsherpa.user.domain.UserJoinRequest;
import bgaebalja.bsherpa.user.dto.request.UserSwaggerLoginRequest;
import bgaebalja.bsherpa.user.dto.response.UserSwaggerLoginResponse;

public interface UserService {

    void saveUser(UserJoinRequest userJoinRequest);

    Object getKakaoUser(String accessToken);

    void saveStudent(UserJoinRequest userJoinRequest);

    UserSwaggerLoginResponse getUser(UserSwaggerLoginRequest userSwaggerLoginRequest);
}
