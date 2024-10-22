package bgaebalja.bsherpa.client.item;

import lombok.Getter;

import java.util.List;

@Getter
public class GetSimilarItemsRequest {
    private List<String> itemIdList;
    private List<String> excludeCode;

    public GetSimilarItemsRequest(List<String> itemIdList, List<String> excludeCode) {
        this.itemIdList = itemIdList;
        this.excludeCode = excludeCode;
    }

    public static GetSimilarItemsRequest of(List<String> itemIds, List<String> excludedItemIds) {
        return new GetSimilarItemsRequest(itemIds, excludedItemIds);
    }
}
