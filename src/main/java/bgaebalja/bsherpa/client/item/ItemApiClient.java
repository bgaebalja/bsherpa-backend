package bgaebalja.bsherpa.client.item;

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
public class ItemApiClient {
    private final RestTemplate restTemplate;

    @Value("${tsherpa.api.url}")
    private String tsherpaUrl;

    @Value("${tsherpa.api.get-items.url}")
    private String getItemsUrl;

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
}
