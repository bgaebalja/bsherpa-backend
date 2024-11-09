package bgaebalja.bsherpa.erorreport.service;

import bgaebalja.bsherpa.erorreport.domain.ErrorReport;
import bgaebalja.bsherpa.erorreport.domain.RegisterErrorReportRequest;
import bgaebalja.bsherpa.erorreport.exception.DuplicatedErrorReportException;
import bgaebalja.bsherpa.erorreport.repository.ErrorReportRepository;
import bgaebalja.bsherpa.exception.UserNotFoundException;
import bgaebalja.bsherpa.file.domain.AddFileRequest;
import bgaebalja.bsherpa.file.service.FileService;
import bgaebalja.bsherpa.question.service.QuestionService;
import bgaebalja.bsherpa.user.domain.Users;
import bgaebalja.bsherpa.user.repository.UserRepository;
import bgaebalja.bsherpa.util.FormatConverter;
import bgaebalja.bsherpa.util.FormatValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static bgaebalja.bsherpa.erorreport.exception.ExceptionMessage.DUPLICATED_ERROR_REPORT_EXCEPTION_MESSAGE;
import static bgaebalja.bsherpa.exception.ExceptionMessage.USER_NOT_FOUND_EXCEPTION_MESSAGE;
import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Propagation.NESTED;

@Service
@RequiredArgsConstructor
public class ErrorReportServiceImpl implements ErrorReportService {
    private final FileService fileService;
    private final QuestionService questionService;
    private final UserRepository userRepository;
    private final ErrorReportRepository errorReportRepository;

    @Override
    @Transactional(isolation = READ_COMMITTED, propagation = NESTED, timeout = 20)
    public void createErrorReport(RegisterErrorReportRequest registerErrorReportRequest) {
        String userEmail = registerErrorReportRequest.getUserEmail();
        Users user = userRepository.findByUserId(userEmail)
                .orElseThrow(
                        () -> new UserNotFoundException(String.format(USER_NOT_FOUND_EXCEPTION_MESSAGE, userEmail))
                );
        Long itemId = FormatConverter.parseToLong(registerErrorReportRequest.getItemId());
        if (errorReportRepository.existsByReporterIdAndItemId(user.getId(), itemId)) {
            throw new DuplicatedErrorReportException(
                    String.format(DUPLICATED_ERROR_REPORT_EXCEPTION_MESSAGE, user.getUserId(), itemId)
            );
        }

        ErrorReport errorReport = ErrorReport.from(registerErrorReportRequest, user);
        int errorReportCount = errorReportRepository.countByItemId(itemId);

        if (FormatValidator.hasValue(errorReportCount)) {
            errorReport.addCount(errorReportCount);
        }

        Long id = errorReportRepository.save(errorReport).getId();
        fileService.createFile(AddFileRequest.from(registerErrorReportRequest, id.toString()));
        questionService.addErrorReportCount(itemId);
    }
}
