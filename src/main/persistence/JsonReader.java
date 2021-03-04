package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.QuestionBank;
import org.json.*;

// Referenced JsonSerializationDemo
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads question bank from file and returns it;
    // throws IOException if an error occurs reading data from file
    public QuestionBank read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseQuestionBank(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses questions from JSON object and returns it
    private QuestionBank parseQuestionBank(JSONObject jsonObject) {
        QuestionBank qb = new QuestionBank();
        addQuestions(qb, jsonObject);
        return qb;
    }

    // MODIFIES: qb
    // EFFECTS: parses questions from JSON object and adds them to question bank
    private void addQuestions(QuestionBank qb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("questions");
        for (Object json : jsonArray) {
            JSONObject nextQuestion = (JSONObject) json;
            addQuestion(qb, nextQuestion);
        }
    }

    // MODIFIES: qb
    // EFFECTS: parses question from JSON object and adds it to question bank
    private void addQuestion(QuestionBank qb, JSONObject jsonObject) {
        String prompt = jsonObject.getString("prompt");
        String answer = String.valueOf(jsonObject.getString("answer"));
        qb.addQuestion(prompt,answer);
    }
}
