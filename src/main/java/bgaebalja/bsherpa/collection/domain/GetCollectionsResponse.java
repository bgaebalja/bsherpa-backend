package bgaebalja.bsherpa.collection.domain;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetCollectionsResponse {
    private final List<GetCollectionResponse> getCollectionResponses;

    private GetCollectionsResponse(List<GetCollectionResponse> getCollectionResponses) {
        this.getCollectionResponses = getCollectionResponses;
    }

    public static GetCollectionsResponse from(List<Collection> collections) {
        return new GetCollectionsResponse(
                collections.stream().map(GetCollectionResponse::from).collect(Collectors.toList())
        );
    }
}
