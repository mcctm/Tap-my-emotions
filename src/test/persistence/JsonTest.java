package persistence;

import model.Question;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Referenced JsonSerializationDemo
public class JsonTest {
    protected void checkQuestion(String prompt, String answer, Question question) {
        assertEquals(prompt, question.getPrompt());
        assertEquals(answer, question.getAnswer());
    }
}
