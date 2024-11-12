package bgaebalja.bsherpa.file.controller;

import bgaebalja.bsherpa.file.domain.AddFileRequest;
import bgaebalja.bsherpa.file.domain.AddFileResponse;
import bgaebalja.bsherpa.file.domain.UserFile;
import bgaebalja.bsherpa.file.service.FileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.HttpStatus.CREATED;

@Controller
@RequestMapping("/images")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    private static final String ADD_FILE = "파일 추가";
    private static final String ADD_FILE_DESCRIPTION = "파일과 대상 타입, 대상 ID를 입력해 파일을 추가할 수 있습니다.";
    private static final String ADD_FILE_FORM = "파일 추가 양식";

    @ApiOperation(value = ADD_FILE, notes = ADD_FILE_DESCRIPTION)
    @PostMapping()
    public ResponseEntity<AddFileResponse> addFile(
            @ModelAttribute @Parameter(description = ADD_FILE_FORM) AddFileRequest addFileRequest
    ) {
        UserFile userFile = fileService.createFile(addFileRequest);
        return ResponseEntity.status(CREATED).body(AddFileResponse.from(userFile));
    }
}
