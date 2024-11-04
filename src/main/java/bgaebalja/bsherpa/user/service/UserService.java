package bgaebalja.bsherpa.user.service;

import bgaebalja.bsherpa.user.domain.UserJoinRequest;

public interface UserService {

    void saveUser(UserJoinRequest userJoinRequest);

    Object getKakaoUser(String accessToken);

    void saveStudent(UserJoinRequest userJoinRequest);
}
