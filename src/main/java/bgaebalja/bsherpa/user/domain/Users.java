package bgaebalja.bsherpa.user.domain;

import static lombok.AccessLevel.PROTECTED;

import bgaebalja.bsherpa.audit.BaseGeneralEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Users extends BaseGeneralEntity {

  @Id @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String userId;

  public static Users createUser(String username, String password, String userId) {
    return Users.builder()
        .username(username)
        .password(password)
        .userId(userId).build();
  }
}
