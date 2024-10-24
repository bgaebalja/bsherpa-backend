package bgaebalja.bsherpa.step1.controller;

import bgaebalja.bsherpa.client.chapter.ChapterApiClient;
import bgaebalja.bsherpa.client.chapter.GetChaptersResponse;
import bgaebalja.bsherpa.step1.domain.ChapterDTO;
import bgaebalja.bsherpa.step1.domain.Step1ResponseDTO;
import bgaebalja.bsherpa.step1.domain.StepRequestDTO;
import bgaebalja.bsherpa.step1.service.Step1Service;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@Slf4j
@RequiredArgsConstructor
public class Step1Controller {


    // 조요한이 만든 RestTemplate private final Step1Service step1Service;

    private final ChapterApiClient chapterApiClient;

    @PostMapping("step1/chapters")
    public GetChaptersResponse getChapters(){

      //StepRequestDTO dto = new StepRequestDTO();
      //dto.setSubjectId("1154");
      return  chapterApiClient.getChapters("1154");


    }
  }




