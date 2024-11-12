package bgaebalja.bsherpa.client.chapter;

import lombok.Getter;

import java.util.List;

@Getter
public class GetBookResponse {
    private List<GetBookInformationResponse> subjectInfoList;
    private String successYn;
}
