package model;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    protected List<Question> questionSet;

    // EFFECTS: constructs a collection of questions containing two existing questions
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

    // MODIFIES: this
    // EFFECTS: returns the list of all questions added (both the prompt and the answer)
    // The list returned could be empty (it is never null).
    public List<String> listAllQuestions() {

        List listOfAllQuestions = new ArrayList<String>();

        for (int i = 0; i < questionSet.size(); i++) {

            Question question = questionSet.get(i);
            String prompt = question.getPrompt();
            String answer = question.getAnswer();

            listOfAllQuestions.add(prompt);
            listOfAllQuestions.add(answer);

        }

        return listOfAllQuestions;
    }

    // MODIFIES: this
    // EFFECTS: checks if the given string matches the given question's correct answer
    public Boolean checkAnswer(String str, int i) {

        String correctAnswer = questionSet.get(i).getAnswer();

        return str.equals(correctAnswer);

    }


}
