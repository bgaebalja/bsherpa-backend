package bgaebalja.bsherpa.validation;

import io.swagger.v3.oas.annotations.Hidden;
import java.lang.reflect.Field;
import lombok.extern.slf4j.Slf4j;
import org.everit.json.schema.Schema;
import org.json.JSONObject;

@Slf4j
public class CustomValidation {

  private CustomValidation customValidation;

  public boolean validate(Schema schema){
    try {
      JSONObject jsonObject = toJsonObject(schema);
      schema.validate(jsonObject);
    }catch (Exception e){
      log.error("에러",e);
      return false;
    }
    return true;
  }

  private JSONObject toJsonObject(Schema schema) throws Exception{
    JSONObject jsonObject = new JSONObject();
    Field[] fields = customValidation.getClass().getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      if (field.isAnnotationPresent(CustomValidField.class)) {
        CustomValidField fieldAnnotation = field.getAnnotation(CustomValidField.class);
        String fieldName = fieldAnnotation.jsonKeyName();
        Object fieldValue = field.get(customValidation);
        if(!schema.definesProperty(fieldName)){
          log.info("에러");
        }
        jsonObject.put(fieldName, fieldValue);
      }
    }
    return jsonObject;
  }

  @Hidden
  public void setChildInstance(CustomValidation customValidation){
    this.customValidation = customValidation;
  }
}
