package model;

import org.json.JSONObject;
import persistence.Writable;

public class Question implements Writable {

    private String prompt;
    private String answer;

    // EFFECTS: constructs a question (the prompt and the answer)
    public Question(String prompt, String answer) {
        this.prompt = prompt;
        this.answer = answer;
    }

    // EFFECTS: returns the prompt of a given question
    public String getPrompt() {
        return prompt;
    }

    // EFFECTS: returns the answer of a given question
    public String getAnswer() {
        return answer;
    }


    @Override
    // EFFECTS: see interface Writable
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("prompt", prompt);
        json.put("answer", answer);
        return json;
    }
}
