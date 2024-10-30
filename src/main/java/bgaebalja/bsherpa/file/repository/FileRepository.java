package bgaebalja.bsherpa.file.repository;

import bgaebalja.bsherpa.file.domain.TargetType;
import bgaebalja.bsherpa.file.domain.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<UserFile, Long> {
    List<UserFile> findByTargetTypeAndTargetIdAndDeleteYnFalseOrderByCreatedAtDesc(TargetType targetType, Long id);

    Optional<UserFile> findByIdAndDeleteYnFalse(Long id);

    Optional<UserFile> findFirstByTargetTypeAndTargetIdAndDeleteYnFalseOrderByCreatedAt(
            TargetType targetType, Long targetId
    );

    List<UserFile> findByTargetId(Long targetId);
}
