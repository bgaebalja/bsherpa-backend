package bgaebalja.bsherpa.client.itemimage;

import lombok.Getter;

import java.util.List;

@Getter
public class GetItemCountsResponse {
    private List<SmallChapterItemCountResponse> listSmallItemCount;
    private List<TopicChapterItemCountResponse> listTopicItemCount;
    private String successYn;
}
