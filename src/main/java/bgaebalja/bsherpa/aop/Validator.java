package bgaebalja.bsherpa.aop;

import static java.nio.charset.StandardCharsets.UTF_8;

import bgaebalja.bsherpa.validation.CustomValid;
import bgaebalja.bsherpa.validation.CustomValidation;
import bgaebalja.bsherpa.validation.Valid;
import io.micrometer.core.instrument.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy
public class Validator {

  private static final HashMap<Valid, Schema> validJsonSchema = new HashMap<>();

  @Before("@annotation(customValid)")
  public void validate(JoinPoint joinPoint,CustomValid customValid) throws Throwable {
    log.info("Validating {}", customValid.schema());
    Schema schema = validJsonSchema.get(customValid.schema());
    log.info("스키마 {}",schema);
    if (schema == null) return;
    Object[] args = joinPoint.getArgs();
    for (Object param : args) {
      if (param instanceof CustomValidation){
        CustomValidation customValidation = (CustomValidation) param;
        customValidation.setChildInstance(customValidation);
        boolean result = customValidation.validate(schema);
        if (!result) {
          throw new Exception("유효성 검증 위반");
        }
      }
    }
  }

  public static void loadSchema() {

    log.info("Loading JSON Schema");

    for(Valid valid: Valid.values()){
      Resource resource = new ClassPathResource(
          "validate/" + valid.name() + ".json");
      if (resource.exists()) {
        try {
          InputStream inputStream = resource.getInputStream();
          String schemaString = IOUtils.toString(inputStream, UTF_8);
          JSONObject jsonObject = new JSONObject(schemaString);
          Schema schema = SchemaLoader.load(jsonObject);
          validJsonSchema.put(valid, schema);
        }catch (IOException e){
          log.error("에러",e);
        }
      }
    }
  }

}
