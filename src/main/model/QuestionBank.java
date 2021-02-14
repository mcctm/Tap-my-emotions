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
    // EFFECTS: adds a new question to the collection of questions
    public void addQuestion(String prompt, String answer) {
        Question question = new Question(prompt, answer);
        questionSet.add(question);
    }

    public String getQuestionPrompt(int i) {
        String question = questionSet.get(i).getPrompt();
        return question;
    }

    public String getQuestionAnswer(int i) {
        String answer = questionSet.get(i).getAnswer();
        return answer;
    }

    // MODIFIES: this
    // EFFECTS: returns the list of the questions added into the collection of questions
    // the list returned could be empty (it is never null). The list returned consists
    // of both the answer and its prompt
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


    public Boolean checkAnswer(String str, int i) {

        String correctAnswer = questionSet.get(i).getAnswer();

        return str.equals(correctAnswer);

    }


}
