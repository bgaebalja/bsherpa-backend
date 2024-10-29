package bgaebalja.bsherpa.validation;

import static bgaebalja.bsherpa.aop.Validator.*;

import bgaebalja.bsherpa.aop.Validator;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class SchemaInitializer {

  @PostConstruct
  public void initialize() {
    loadSchema();
  }

}
