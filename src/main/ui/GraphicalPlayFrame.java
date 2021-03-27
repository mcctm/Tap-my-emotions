package ui;

import model.QuestionBank;
import persistence.JsonReader;
import persistence.JsonWriter;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphicalPlayFrame implements ActionListener {

    protected static final String JSON_STORE = "./data/questionbank.json";
    protected QuestionBank questionBank;
    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;
    protected JFrame frame;
    protected JPanel panel;

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
        panel.setBackground(Color.getHSBColor(60, 0, 30));

        playGame();

        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new GraphicalPlayFrame();
    }

    // MODIFIES: this
    // EFFECTS: declares and instantiates all labels and submit button for the game
    private void playGame() {
        userAnswersTextField = new ArrayList<>();
        correctAnswers = new ArrayList<>();

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

    // MODIFIES: this
    // EFFECTS: initializes questionBank and loads it from json
    public void init() {
        try {
            Scanner input;
            input = new Scanner(System.in);
            questionBank = new QuestionBank();
            jsonWriter = new JsonWriter(JSON_STORE);
            jsonReader = new JsonReader(JSON_STORE);
            questionBank = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: plays sound when submit button is pressed,
    //          gets textfields of all answered questions to calculate score and provide feedback
    @Override
    public void actionPerformed(ActionEvent e) {
        playSound("./data/tada.wav");
        userAnswersInString = new ArrayList<>();
        for (JTextField field : userAnswersTextField) {
            userAnswersInString.add(field.getText().toLowerCase());
        }
        calculateScoreAndProvideFeedback();

    }

    // MODIFIES: this
    // EFFECTS: helper method to play sound
    public static void playSound(String audio) {
        InputStream music;
        try {
            music = new FileInputStream(new File(audio));
            AudioStream audios = new AudioStream(music);
            AudioPlayer.player.start(audios);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error playing sound");
        }
    }

    // MODIFIES: this
    // EFFECTS: compares players' answers to the correct answer, then calculate their score and provide feedback
    private void calculateScoreAndProvideFeedback() {
        score = 0;
        for (int i = 0; i < userAnswersInString.size(); i++) {
            if (userAnswersInString.get(i).equals(correctAnswers.get(i))) {
                score++;
            }
        }
        scoreFeedback(score);
    }

    // MODIFIES: this
    // EFFECTS: helper method to provide different feedback based on player's score
    public void scoreFeedback(int score) {

        int numberOfQuestions = questionBank.listAllQuestionsInListOfString().size() / 2;

        if (score > numberOfQuestions * 0.8) {
            String feedback = "Awesome work!";
            feedbackMessage(feedback, numberOfQuestions);

        } else if (score > numberOfQuestions * 0.7) {
            String feedback = "Not bad, keep it up!";
            feedbackMessage(feedback, numberOfQuestions);
        } else {
            String feedback = "Try again! Practice makes perfect!";
            feedbackMessage(feedback, numberOfQuestions);
        }
    }

    // MODIFIES: this
    // EFFECTS: helper method to display appropriate feedback message
    public void feedbackMessage(String feedback, int numberOfQuestions) {
        Object[] options = {"Return to homepage"};
        int optionDialog = JOptionPane.showOptionDialog(frame,
                "You got " + score + " / " + numberOfQuestions + " question(s).\n"
                        + feedback,
                "How did you do?",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        if (optionDialog == JOptionPane.YES_OPTION) {
            new GraphicalHomeFrame();
        }
    }

}
