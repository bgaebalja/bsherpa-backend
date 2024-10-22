package bgaebalja.bsherpa.user.repository;

import bgaebalja.bsherpa.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, Long> {

  Users findByEmail(String email);

  @Query("select u from Users u join fetch u.roles r where u.email=:email")
  Users getUserWithRoles(@Param("email") String email);

}
