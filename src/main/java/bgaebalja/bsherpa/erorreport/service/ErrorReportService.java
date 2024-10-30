package bgaebalja.bsherpa.erorreport.service;

import bgaebalja.bsherpa.erorreport.domain.RegisterErrorReportRequest;

public interface ErrorReportService {
    void createErrorReport(RegisterErrorReportRequest registerErrorReportRequest);
}
