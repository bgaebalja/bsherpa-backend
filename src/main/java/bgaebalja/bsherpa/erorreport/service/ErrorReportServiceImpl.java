package bgaebalja.bsherpa.erorreport.service;

import bgaebalja.bsherpa.erorreport.domain.ErrorReport;
import bgaebalja.bsherpa.erorreport.domain.RegisterErrorReportRequest;
import bgaebalja.bsherpa.erorreport.repository.ErrorReportRepository;
import bgaebalja.bsherpa.file.domain.AddFileRequest;
import bgaebalja.bsherpa.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Propagation.NESTED;

@Service
@RequiredArgsConstructor
public class ErrorReportServiceImpl implements ErrorReportService {
    private final FileService fileService;
    private final ErrorReportRepository errorReportRepository;

    @Override
    @Transactional(isolation = READ_COMMITTED, propagation = NESTED, timeout = 20)
    public void createErrorReport(RegisterErrorReportRequest registerErrorReportRequest) {
        Long id = errorReportRepository.save(ErrorReport.from(registerErrorReportRequest)).getId();
        fileService.createFile(AddFileRequest.from(registerErrorReportRequest, id.toString()));
    }
}
