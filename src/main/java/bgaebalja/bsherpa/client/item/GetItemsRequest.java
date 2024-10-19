package bgaebalja.bsherpa.client.item;

import lombok.Getter;

import java.util.List;

@Getter
public class GetItemsRequest {
    List<String> itemIdList;

    private GetItemsRequest(List<String> itemIdList) {
        this.itemIdList = itemIdList;
    }

    public static GetItemsRequest from(List<String> itemIds) {
        return new GetItemsRequest(itemIds);
    }
}
