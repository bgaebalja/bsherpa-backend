package bgaebalja.bsherpa.client.itemimage;

import lombok.Getter;

import java.util.List;

@Getter
public class GetItemImagesResponse {
    private List<GetItemImageResponse> itemList;
    private String successYn;

    public int size() {
        return itemList.size();
    }

    public GetItemImageResponse get(int index) {
        return itemList.get(index);
    }
}
