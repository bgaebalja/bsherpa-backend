package bgaebalja.bsherpa.subject.repository;

import bgaebalja.bsherpa.subject.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, String> {
    List<Subject> findByName(String subjectName);
}
