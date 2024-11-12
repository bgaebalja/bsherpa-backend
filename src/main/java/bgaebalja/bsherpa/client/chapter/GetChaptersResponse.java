package bgaebalja.bsherpa.client.chapter;

import lombok.Getter;

import java.util.List;

@Getter
public class GetChaptersResponse {
    private List<GetChapterResponse> chapterList;
    private String successYn;
}
