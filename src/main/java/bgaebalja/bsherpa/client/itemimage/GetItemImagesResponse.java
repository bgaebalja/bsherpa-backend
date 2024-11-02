package bgaebalja.bsherpa.client.itemimage;

import bgaebalja.bsherpa.client.item.GetChapterItemsRequest;
import bgaebalja.bsherpa.util.FormatConverter;
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

    public void adjustData(GetChapterItemsRequest getChapterItemsRequest) {
        List<String> requestedLevelCnt = getChapterItemsRequest.getLevelCnt();
        int[] responseLevelCnt = new int[requestedLevelCnt.size()];

        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getDifficultyCode().equals("01")) {
                i = removeItemOrAddCount(requestedLevelCnt, responseLevelCnt, 0, i);
                continue;
            }

            if (itemList.get(i).getDifficultyCode().equals("02")) {
                i = removeItemOrAddCount(requestedLevelCnt, responseLevelCnt, 1, i);
                continue;
            }

            if (itemList.get(i).getDifficultyCode().equals("03")) {
                i = removeItemOrAddCount(requestedLevelCnt, responseLevelCnt, 2, i);
                continue;
            }

            if (itemList.get(i).getDifficultyCode().equals("04")) {
                i = removeItemOrAddCount(requestedLevelCnt, responseLevelCnt, 3, i);
                continue;
            }

            if (itemList.get(i).getDifficultyCode().equals("05")) {
                i = removeItemOrAddCount(requestedLevelCnt, responseLevelCnt, 4, i);
            }
        }
    }

    private int removeItemOrAddCount(
            List<String> requestedLevelCnt, int[] responseLevelCnt, int levelCountIndex, int index
    ) {
        if (responseLevelCnt[levelCountIndex] >= FormatConverter.parseToInt(requestedLevelCnt.get(levelCountIndex))) {
            itemList.remove(index--);
            return index;
        }

        ++responseLevelCnt[levelCountIndex];
        return index;
    }
}
