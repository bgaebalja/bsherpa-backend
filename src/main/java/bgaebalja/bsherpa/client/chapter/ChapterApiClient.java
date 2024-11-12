package bgaebalja.bsherpa.client.chapter;

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
public class ChapterApiClient {
    private final RestTemplate restTemplate;

    @Value("${tsherpa.api.url}")
    private String tsherpaUrl;

    @Value("${tsherpa.api.get-chapters.url}")
    private String getChaptersUrl;

    @Value("${tsherpa.api.get-evaluations.url}")
    private String getEvaluationsUrl;

    @Value("${tsherpa.api.get-book.url}")
    private String getBookUrl;

    @Value("${tsherpa.api.get-book-exams.url}")
    private String getExamsUrl;

    public GetChaptersResponse getChapters(String subjectId) {
        String url = String.format("%s/%s", tsherpaUrl, getChaptersUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(Map.of(SUBJECT_ID, subjectId), headers);

        ResponseEntity<GetChaptersResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                GetChaptersResponse.class
        );

        return responseEntity.getBody();
    }

    public GetEvaluationsResponse getEvaluations(String subjectId) {
        String url = String.format("%s/%s", tsherpaUrl, getEvaluationsUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(Map.of(SUBJECT_ID, subjectId), headers);

        ResponseEntity<GetEvaluationsResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                GetEvaluationsResponse.class
        );

        return responseEntity.getBody();
    }

    public GetBookResponse getBook(String subjectId) {
        String url = String.format("%s/%s", tsherpaUrl, getBookUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(Map.of(SUBJECT_ID, subjectId), headers);

        ResponseEntity<GetBookResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                GetBookResponse.class
        );

        return responseEntity.getBody();
    }

    public GetExamsResponse getExams(String subjectId) {
        String url = String.format("%s/%s", tsherpaUrl, getExamsUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(Map.of(SUBJECT_ID, subjectId), headers);

        ResponseEntity<GetExamsResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                GetExamsResponse.class
        );

        return responseEntity.getBody();
    }
}
