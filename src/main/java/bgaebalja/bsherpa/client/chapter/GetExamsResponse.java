package bgaebalja.bsherpa.client.chapter;

import lombok.Getter;

import java.util.List;

@Getter
public class GetExamsResponse {
    private List<GetExamResponse> examList;
    private String successYn;
}
