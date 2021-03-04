package persistence;

import model.Question;
import model.QuestionBank;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Referenced JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            QuestionBank qb = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyQuestionBank() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyQuestionBank.json");
        try {
            QuestionBank qb = reader.read();
            assertEquals(0, qb.listAllQuestionsInListOfString().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralQuestionBank() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralQuestionBank.json");
        try {
            QuestionBank questionBank = reader.read();
            List<Question> questionSet = questionBank.getQuestionSetInListOfQuestion();
            assertEquals(2, questionSet.size());
            checkQuestion("I am happy. How do I feel?", "joyful", questionSet.get(0));
            checkQuestion("He feels like crying. How does he feel?", "sad", questionSet.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}