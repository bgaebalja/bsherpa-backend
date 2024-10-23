package bgaebalja.bsherpa.client.exam;

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
public class ExamApiClient {
    private final RestTemplate restTemplate;

    @Value("${tsherpa.api.url}")
    private String tsherpaUrl;

    @Value("${tsherpa.api.get-exam-preview.url}")
    private String getExamPreviewUrl;

    public GetPreviewResponse getPreview(GetExamPreviewRequest getExamPreviewRequest) {
        String url = String.format("%s/%s", tsherpaUrl, getExamPreviewUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON);

        HttpEntity<GetExamPreviewRequest> requestEntity = new HttpEntity<>(getExamPreviewRequest, headers);

        ResponseEntity<GetPreviewResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                GetPreviewResponse.class
        );

        return responseEntity.getBody();
    }
}
