package bgaebalja.bsherpa.user.domain;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Getter
public class UserRole {

  private String role;

}
