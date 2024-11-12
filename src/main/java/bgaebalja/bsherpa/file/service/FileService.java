package bgaebalja.bsherpa.file.service;

import bgaebalja.bsherpa.file.domain.AddFileRequest;
import bgaebalja.bsherpa.file.domain.TargetType;
import bgaebalja.bsherpa.file.domain.UserFile;

import java.util.List;

public interface FileService {
    UserFile createFile(AddFileRequest addFileRequest);

    List<UserFile> getFiles(TargetType targetType, Long targetId);
}
