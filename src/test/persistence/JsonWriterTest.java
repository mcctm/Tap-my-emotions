package persistence;

import model.Question;
import model.QuestionBank;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Referenced JsonSerializationDemo
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            QuestionBank questionBank = new QuestionBank();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyQuestionBank() {
        try {
            QuestionBank questionBank = new QuestionBank();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyQuestionBank.json");
            writer.open();
            writer.write(questionBank);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyQuestionBank.json");
            questionBank = reader.read();
            assertEquals(0, questionBank.getQuestionSetInListOfQuestion().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            QuestionBank questionBank = new QuestionBank();
            questionBank.addQuestion("Ben is left alone in a haunted house. How might he feel?",
                    "scared");
            questionBank.addQuestion("Susan's mum is bringing her to DisneyLand tomorrow! How would she feel?",
                    "excited");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralQuestionBank.json");
            writer.open();
            writer.write(questionBank);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralQuestionBank.json");
            questionBank = reader.read();
            List<Question> questionSet = questionBank.getQuestionSetInListOfQuestion();
            assertEquals(2, questionSet.size());
            checkQuestion("Ben is left alone in a haunted house. How might he feel?",
                    "scared", questionSet.get(0));
            checkQuestion("Susan's mum is bringing her to DisneyLand tomorrow! How would she feel?",
                    "excited", questionSet.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}