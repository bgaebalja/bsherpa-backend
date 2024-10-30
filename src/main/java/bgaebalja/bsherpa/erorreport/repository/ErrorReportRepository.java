package bgaebalja.bsherpa.erorreport.repository;

import bgaebalja.bsherpa.erorreport.domain.ErrorReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorReportRepository extends JpaRepository<ErrorReport, Long> {
}
