package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GraphicalPlayFrame extends GraphicalHomeFrame implements ActionListener {

    private JLabel questionPrompt;
    private JLabel answerLabel;
    private JButton submitButton;
    private List<JTextField> userAnswersTextField;
    private List<String> userAnswersInString;
    private List<String> correctAnswers;
    private Integer score;

    public GraphicalPlayFrame() {
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);
        panel.setBackground(Color.getHSBColor(60, 0, 50));

        playGame();

        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new GraphicalPlayFrame();
    }

    private void playGame() {

        init();
        int numberOfQuestions = questionBank.listAllQuestionsInListOfString().size() / 2;

        userAnswersTextField = new ArrayList<>();
        correctAnswers = new ArrayList<>();

        for (int i = 0; i < numberOfQuestions; i++) {

            questionPrompt = new JLabel(questionBank.getQuestionPrompt(i));
            questionPrompt.setBounds(10, 20 + 100 * i, 1000, 25);
            panel.add(questionPrompt);

            answerLabel = new JLabel("Choices: " + questionBank.getQuestionAnswer(i) + " / " + "angry"
                    + " / " + "nervous");
            answerLabel.setBounds(10, 50 + 100 * i, 1000, 25);
            panel.add(answerLabel);

            JTextField userAnswer = new JTextField();
            userAnswer.setBounds(65, 80 + 100 * i, 165, 25);
            panel.add(userAnswer);

            userAnswersTextField.add(userAnswer);
            correctAnswers.add(questionBank.getQuestionAnswer(i));

        }

        submitButton = new JButton("Submit");
        submitButton.setBounds(150, 700, 165, 25);
        submitButton.addActionListener(this);
        panel.add(submitButton);

    }


    // EFFECTS: Provide different feedbacks based on player's score
    public void scoreFeedback(int score) {

        int numberOfQuestions = questionBank.listAllQuestionsInListOfString().size() / 2;

        if (score > numberOfQuestions * 0.8) {
            System.out.println("You got " + score + " / " + numberOfQuestions + " question(s). Awesome work!");
        } else if (score > numberOfQuestions * 0.7) {
            System.out.println("You got " + score + " / " + numberOfQuestions + " question(s). Not bad, "
                    + "keep it up!");
        } else {
            System.out.println("You got " + score + " / " + numberOfQuestions + " question(s). Try again! "
                    + "Practice makes perfect!");

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        userAnswersInString = new ArrayList<>();
        for (JTextField field : userAnswersTextField) {
            userAnswersInString.add(field.getText().toLowerCase());
        }
        calculateScoreAndProvideFeedback();

    }

    private void calculateScoreAndProvideFeedback() {
        score = 0;
        for (int i = 0; i < userAnswersInString.size(); i++) {
            if (userAnswersInString.get(i).equals(correctAnswers.get(i))) {
                score++;
            }
        }
        scoreFeedback(score);
    }

}
