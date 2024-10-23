package bgaebalja.bsherpa.client.chapter;

import lombok.Getter;

import java.util.List;

@Getter
public class GetEvaluationsResponse {
    private List<GetEvaluationResponse> evaluationList;
}
