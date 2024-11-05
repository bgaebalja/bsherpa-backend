package bgaebalja.bsherpa.option.domain;

import lombok.Getter;

@Getter
public class GetOptionResponse {
    private String optionNo;
    private String html;

    private GetOptionResponse(String optionNo, String html) {
        this.optionNo = optionNo;
        this.html = html;
    }

    public static GetOptionResponse from(Option option) {
        return new GetOptionResponse(option.getOptionNo(), option.getHtml());
    }
}
