package bgaebalja.bsherpa.client.item;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static bgaebalja.bsherpa.util.RequestConstant.APPLICATION_JSON;
import static bgaebalja.bsherpa.util.RequestConstant.CONTENT_TYPE;

@Component
@RequiredArgsConstructor
public class ItemApiClient {
    private final RestTemplate restTemplate;

    @Value("${tsherpa.api.url}")
    private String tsherpaUrl;

    @Value("${tsherpa.api.get-items.url}")
    private String getItemsUrl;

    @Value("${tsherpa.api.get-chapter-items.url}")
    private String getChapterItemsUrl;

    @Value("${tsherpa.api.get-exam-items.url}")
    private String getExamItemsUrl;

    @Value("${tsherpa.api.get-similar-items.url}")
    private String getSimilarItemsUrl;

    private static final String EXAM_ID = "examId";

    public GetItemsResponse getItems(GetItemsRequest getItemsRequest) {
        String url = String.format("%s/%s", tsherpaUrl, getItemsUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON);

        HttpEntity<GetItemsRequest> requestEntity = new HttpEntity<>(getItemsRequest, headers);

        ResponseEntity<GetItemsResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                GetItemsResponse.class
        );

        return responseEntity.getBody();
    }

    public GetItemsResponse getChapterItems(GetChapterItemsRequest getChapterItemsRequest) {
        String url = String.format("%s/%s", tsherpaUrl, getChapterItemsUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON);

        HttpEntity<GetChapterItemsRequest> requestEntity = new HttpEntity<>(getChapterItemsRequest, headers);

        ResponseEntity<GetItemsResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                GetItemsResponse.class
        );

        return responseEntity.getBody();
    }

    public GetItemsResponse getExamItems(String examId) {
        String url = String.format("%s/%s", tsherpaUrl, getExamItemsUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(Map.of(EXAM_ID, examId), headers);

        ResponseEntity<GetItemsResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                GetItemsResponse.class
        );

        return responseEntity.getBody();
    }

    public GetItemsResponse getSimilarItems(GetSimilarItemsRequest getSimilarItemsRequest) {
        String url = String.format("%s/%s", tsherpaUrl, getSimilarItemsUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON);

        HttpEntity<GetSimilarItemsRequest> requestEntity = new HttpEntity<>(getSimilarItemsRequest, headers);

        ResponseEntity<GetItemsResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                GetItemsResponse.class
        );

        return responseEntity.getBody();
    }
}
