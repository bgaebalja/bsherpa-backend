package bgaebalja.bsherpa.client.itemimage;

import bgaebalja.bsherpa.client.item.GetChapterItemsRequest;
import bgaebalja.bsherpa.client.item.GetItemsRequest;
import bgaebalja.bsherpa.client.item.GetSimilarItemsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static bgaebalja.bsherpa.util.RequestConstant.*;

@Component
@RequiredArgsConstructor
public class ItemImageApiClient {
    private final RestTemplate restTemplate;

    @Value("${tsherpa.api.url}")
    private String tsherpaUrl;

    @Value("${tsherpa.api.get-item-images.url}")
    private String getItemImagesUrl;

    @Value("${tsherpa.api.get-chapter-item-images.url}")
    private String getChapterItemImagesUrl;

    @Value("${tsherpa.api.get-exam-item-images.url}")
    private String getExamItemImagesUrl;

    @Value("${tsherpa.api.get-similar-item-images.url}")
    private String getSimilarItemImagesUrl;

    @Value("${tsherpa.api.get-item-counts.url}")
    private String getItemCountsUrl;

    public GetItemImagesResponse getItemImages(GetItemsRequest getItemsRequest) {
        String url = String.format("%s/%s", tsherpaUrl, getItemImagesUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON);

        HttpEntity<GetItemsRequest> requestEntity = new HttpEntity<>(getItemsRequest, headers);

        ResponseEntity<GetItemImagesResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                GetItemImagesResponse.class
        );

        return responseEntity.getBody();
    }

    public GetItemImagesResponse getChapterItemImages(GetChapterItemsRequest getChapterItemsRequest) {
        String url = String.format("%s/%s", tsherpaUrl, getChapterItemImagesUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON);

        HttpEntity<GetChapterItemsRequest> requestEntity = new HttpEntity<>(getChapterItemsRequest, headers);

        ResponseEntity<GetItemImagesResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                GetItemImagesResponse.class
        );

        return responseEntity.getBody();
    }

    public GetItemImagesResponse getExamItemImages(String examId) {
        String url = String.format("%s/%s", tsherpaUrl, getExamItemImagesUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(Map.of(EXAM_ID, examId), headers);

        ResponseEntity<GetItemImagesResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                GetItemImagesResponse.class
        );

        return responseEntity.getBody();
    }

    public GetItemImagesResponse getSimilarItemImages(GetSimilarItemsRequest getSimilarItemsRequest) {
        String url = String.format("%s/%s", tsherpaUrl, getSimilarItemImagesUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON);

        HttpEntity<GetSimilarItemsRequest> requestEntity = new HttpEntity<>(getSimilarItemsRequest, headers);

        ResponseEntity<GetItemImagesResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                GetItemImagesResponse.class
        );

        return responseEntity.getBody();
    }

    public GetItemCountsResponse getItemCounts(GetItemCountsRequest getItemCountsRequest) {
        String url = String.format("%s/%s", tsherpaUrl, getItemCountsUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON);

        HttpEntity<GetItemCountsRequest> requestEntity = new HttpEntity<>(getItemCountsRequest, headers);

        ResponseEntity<GetItemCountsResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                GetItemCountsResponse.class
        );

        return responseEntity.getBody();
    }
}
