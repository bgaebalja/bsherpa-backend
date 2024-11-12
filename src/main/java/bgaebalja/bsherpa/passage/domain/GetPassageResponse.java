package bgaebalja.bsherpa.passage.domain;

import bgaebalja.bsherpa.util.FormatConverter;
import lombok.Getter;

@Getter
public class GetPassageResponse {
    private Long id;
    private Long passageId;
    private String html;
    private String url;

    private GetPassageResponse(Long id, Long passageId, String html, String url) {
        this.id = id;
        this.passageId = passageId;
        this.html = html;
        this.url = url;
    }

    public static GetPassageResponse from(Passage passage) {
        return new GetPassageResponse(
                passage.getId(), FormatConverter.parseToLong(passage.getPassageId()),
                passage.getHtml(), passage.getUrl()
        );
    }
}
