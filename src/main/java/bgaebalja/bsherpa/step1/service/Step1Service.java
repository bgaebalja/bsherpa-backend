package bgaebalja.bsherpa.step1.service;

import bgaebalja.bsherpa.step1.domain.ChapterDTO;
import bgaebalja.bsherpa.step1.domain.Step1ResponseDTO;
import bgaebalja.bsherpa.step1.domain.StepRequestDTO;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class Step1Service {



  public Step1ResponseDTO getChapters(@RequestBody StepRequestDTO dto) {
    URI uri = UriComponentsBuilder
        .fromUriString("https://tsherpa.item-factory.com")
        .path("chapter/chapter-list")
        .encode()
        .build()
        .toUri();

    // HTTP 헤더 설정 추가
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // HTTP 엔티티 생성
    HttpEntity<StepRequestDTO> requestEntity = new HttpEntity<>(dto, headers);

    RestTemplate restTemplate = new RestTemplate();

    try {
      // exchange 메서드 사용하여 더 자세한 응답 확인 가능
      ResponseEntity<Step1ResponseDTO> response = restTemplate.exchange(
          uri,
          HttpMethod.POST,
          requestEntity,
          Step1ResponseDTO.class
      );

      log.info("Status Code: {}", response.getStatusCode());
      log.info("Response Body: {}", response.getBody());

      return response.getBody();    }
    catch (RestClientException e) {
      log.error("API 호출 에러: ", e);
      throw e;
    }
  }




}
