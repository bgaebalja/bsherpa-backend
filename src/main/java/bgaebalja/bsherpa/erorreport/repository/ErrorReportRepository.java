package bgaebalja.bsherpa.erorreport.repository;

import bgaebalja.bsherpa.erorreport.domain.ErrorReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ErrorReportRepository extends JpaRepository<ErrorReport, Long> {
    boolean existsByReporterIdAndItemId(@Param("reporter_id") Long reporterId, @Param("item_id") Long itemId);
}
