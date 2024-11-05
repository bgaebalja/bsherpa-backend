package bgaebalja.bsherpa.option.domain;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetOptionsResponse {
    private List<GetOptionResponse> getOptiosResponses;

    private GetOptionsResponse(List<GetOptionResponse> getOptiosResponses) {
        this.getOptiosResponses = getOptiosResponses;
    }

    public static GetOptionsResponse from(List<Option> options) {
        return new GetOptionsResponse(options.stream().map(GetOptionResponse::from).collect(Collectors.toList()));
    }
}
