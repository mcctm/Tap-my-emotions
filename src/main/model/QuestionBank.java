package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

public class QuestionBank implements Writable {

    protected List<Question> questionSet;

    // EFFECTS: constructs a list of Questions
    public QuestionBank() {
        this.questionSet = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a new Question (prompt and answer) to the collection of questions
    public void addQuestion(String prompt, String answer) {
        Question question = new Question(prompt,answer);
        questionSet.add(question);
    }

    // EFFECTS: returns the prompt of a specific Question in the QuestionBank
    public String getQuestionPrompt(int i) {
        return questionSet.get(i).getPrompt();
    }

    // EFFECTS: returns the answer of a specific Question in the QuestionBank
    public String getQuestionAnswer(int i) {
        return questionSet.get(i).getAnswer();
    }

    // EFFECTS: returns an unmodifiable list of questions in this question bank
    public List<Question> getQuestionSetInListOfQuestion() {
        return Collections.unmodifiableList(questionSet);
    }

    // MODIFIES: this
    // EFFECTS: returns the list of all questions added (both the prompt and the answer)
    // The list returned could be empty (it is never null).
    public List<String> listAllQuestionsInListOfString() {

        ArrayList listOfAllQuestions = new ArrayList<String>();

        for (int i = 0; i < questionSet.size(); i++) {

            Question question = questionSet.get(i);
            String prompt = question.getPrompt();
            String answer = question.getAnswer();

            listOfAllQuestions.add(prompt);
            listOfAllQuestions.add(answer);

        }
        Collections.shuffle(questionSet, new Random());
        return listOfAllQuestions;
    }

    // EFFECTS: checks if the given string matches the given question's correct answer
    public Boolean checkAnswer(String str, int i) {

        String correctAnswer = questionSet.get(i).getAnswer();

        return str.equals(correctAnswer);

    }

    @Override
    // EFFECTS: see interface Writable
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("questions", questionsToJson());
        return json;
    }

    // EFFECTS: returns questions in this question set as a JSON array
    private JSONArray questionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Question q : questionSet) {
            jsonArray.put(q.toJson());
        }

        return jsonArray;
    }
}
