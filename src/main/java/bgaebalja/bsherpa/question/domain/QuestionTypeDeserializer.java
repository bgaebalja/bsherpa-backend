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
            case "자유선지형":
                return QuestionType.FREE_CHOICE;
            case "2지 선택":
                return QuestionType.CHOICE_FROM_TWO;
            case "3지 선택":
                return QuestionType.CHOICE_FROM_THREE;
            case "4지 선택":
                return QuestionType.CHOICE_FROM_FOUR;
            case "5지 선택":
                return QuestionType.CHOICE_FROM_FIVE;
            case "단답 유순형":
                return QuestionType.ORDERED_SUBJECTIVE;
            case "단답 무순형":
                return QuestionType.UNORDERED_SUBJECTIVE;
            case "단답 그룹형":
                return QuestionType.GROUPED_SUBJECTIVE;
            case "논술형":
                return QuestionType.ESSAY;
            case "선택채움형":
                return QuestionType.FILLING_SELECTION;
            case "서술형":
                return QuestionType.DESCRIPTIVE;
            case "드래그 드랍":
                return QuestionType.DRAG_DROP;
            case "영역 선택형":
                return QuestionType.AREA_SELECTION;
            case "선잇기형":
                return QuestionType.CONNECTING_LINE;
            case "기타":
                return QuestionType.ETC;
            default:
                throw new IllegalArgumentException("Unknown value: " + value);
        }
    }
}