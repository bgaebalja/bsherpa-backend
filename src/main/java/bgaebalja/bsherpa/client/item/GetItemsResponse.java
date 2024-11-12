package bgaebalja.bsherpa.client.item;

import lombok.Getter;

import java.util.List;

@Getter
public class GetItemsResponse {
    private List<GetItemResponse> itemList;
    private String successYn;

    public int size() {
        return itemList.size();
    }

    public GetItemResponse get(int index) {
        return itemList.get(index);
    }
}
