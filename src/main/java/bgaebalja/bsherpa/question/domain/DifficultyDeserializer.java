package bgaebalja.bsherpa.question.domain;

import bgaebalja.bsherpa.question.exception.DifficultyNotFoundException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class DifficultyDeserializer extends JsonDeserializer<Difficulty> {
    @Override
    public Difficulty deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        String value = jsonParser.getText();

        for (Difficulty difficulty : Difficulty.values()) {
            if (difficulty.getName().equals(value) || difficulty.getCode().equals(value)) {
                return difficulty;
            }
        }

        throw new DifficultyNotFoundException(String.format("Difficulty not found for value: %s", value));
    }
}