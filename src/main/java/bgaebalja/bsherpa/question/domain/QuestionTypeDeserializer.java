package bgaebalja.bsherpa.question.domain;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class QuestionTypeDeserializer extends JsonDeserializer<QuestionType> {
    @Override
    public QuestionType deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        String value = jsonParser.getText().trim(); // 공백 제거

        switch (value) {
            case "10":
                return QuestionType.FREE_CHOICE;
            case "20":
                return QuestionType.CHOICE_FROM_TWO;
            case "30":
                return QuestionType.CHOICE_FROM_THREE;
            case "40":
                return QuestionType.CHOICE_FROM_FOUR;
            case "50":
                return QuestionType.CHOICE_FROM_FIVE;
            case "60":
                return QuestionType.ORDERED_SUBJECTIVE;
            case "61":
                return QuestionType.UNORDERED_SUBJECTIVE;
            case "63":
                return QuestionType.GROUPED_SUBJECTIVE;
            case "70":
                return QuestionType.ESSAY;
            case "84":
                return QuestionType.FILLING_SELECTION;
            case "85":
                return QuestionType.DESCRIPTIVE;
            case "86":
                return QuestionType.DRAG_DROP;
            case "87":
                return QuestionType.AREA_SELECTION;
            case "88":
                return QuestionType.CONNECTING_LINE;
            case "99":
                return QuestionType.ETC;
            default:
                throw new IllegalArgumentException("Unknown value: " + value);
        }
    }
}