package bgaebalja.bsherpa.passage.domain;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetPassagesResponse {
    private final List<GetPassageResponse> getPassageResponses;

    private GetPassagesResponse(List<GetPassageResponse> getPassageResponses) {
        this.getPassageResponses = getPassageResponses;
    }

    public static GetPassagesResponse from(List<Passage> passages) {
        return new GetPassagesResponse(
                passages.stream().map(GetPassageResponse::from).collect(Collectors.toList())
        );
    }
}
