package bgaebalja.bsherpa.user.domain;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

import bgaebalja.bsherpa.audit.BaseGeneralEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder @Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Users extends BaseGeneralEntity {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String userId;

  @Column(name = "class")
  private String clazz;

  private String grade;

  @Builder.Default
  @ElementCollection(fetch = LAZY)
  @CollectionTable(name = "user_role", joinColumns = {
      @JoinColumn(name = "user_id")
  })
  private List<UserRole> roles = new ArrayList<>();

  public static Users createUser(String username, String password, String email,String clazz,String grade) {
    Users user = Users.builder()
        .username(username)
        .password(password)
        .clazz(clazz)
        .grade(grade)
        .userId(email)
        .build();
    user.getRoles().add(new UserRole("CREATOR"));
    return user;
  }
  public static Users createStudent(String username, String password, String email,String clazz,String grade) {
    Users user = createUser(username, password, email, clazz, grade);
    user.getRoles().add(new UserRole("STUDENT"));
    return user;
  }
}
