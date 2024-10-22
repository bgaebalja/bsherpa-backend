package bgaebalja.bsherpa.client.itemimage;

import bgaebalja.bsherpa.client.item.GetChapterItemsRequest;
import bgaebalja.bsherpa.client.item.GetItemsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static bgaebalja.bsherpa.util.RequestConstant.APPLICATION_JSON;
import static bgaebalja.bsherpa.util.RequestConstant.CONTENT_TYPE;

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
}
