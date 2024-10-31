package bgaebalja.bsherpa.passage.service;

import bgaebalja.bsherpa.passage.repository.PassageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassageServiceImpl implements PassageService{
    private final PassageRepository passageRepository;

}
