package bgaebalja.bsherpa.file.domain;

import lombok.Getter;

@Getter
public class AddFileResponse {
    private Long id;
    private String url;

    private AddFileResponse(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public static AddFileResponse from(UserFile userFile) {
        return new AddFileResponse(userFile.getId(), userFile.getS3Url());
    }
}
