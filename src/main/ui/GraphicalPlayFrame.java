package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class GraphicalPlayFrame extends GraphicalHomeFrame implements ActionListener {

    private JLabel questionPrompt;
    private JLabel answerLabel;
    private JTextField userAnswerText;
    private JButton submitButton;

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

        for (int i = 0; i < numberOfQuestions; i++) {

            questionPrompt = new JLabel(questionBank.getQuestionPrompt(i));
            questionPrompt.setBounds(10, 20 + 100 * i, 1000, 25);
            panel.add(questionPrompt);

            answerLabel = new JLabel("Choices: " + questionBank.getQuestionAnswer(i) + " / " + "angry"
                    + " / " + "nervous");
            answerLabel.setBounds(10, 50 + 100 * i, 1000, 25);
            panel.add(answerLabel);

            userAnswerText = new JTextField(100);
            userAnswerText.setBounds(65, 80 + 100 * i, 165, 25);
            panel.add(userAnswerText);

        }
        submitButton = new JButton("Check how you did!");
        submitButton.setBounds(120, 100 + 100 * numberOfQuestions, 180, 25);
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

    }

}
