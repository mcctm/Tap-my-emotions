package ui;

import model.QuestionBank;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GraphicalDesignFrame implements ActionListener {

    protected static final String JSON_STORE = "./data/questionbank.json";
    protected QuestionBank questionBank;
    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;
    protected JFrame frame;
    protected JPanel panel;

    private JLabel questionLabel;
    private JLabel answerLabel;
    private JTextField userQuestionText;
    private JTextField userAnswerText;
    private JButton submitButton;

    public GraphicalDesignFrame() {
        init();
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);
        panel.setBackground(Color.getHSBColor(60, 0, 50));

        questionTool();
        answerTool();
        submitTool();

        frame.setVisible(true);

    }

    public void questionTool() {
        questionLabel = new JLabel("Enter question:");
        questionLabel.setBounds(10, 20, 200, 25);
        panel.add(questionLabel);

        userQuestionText = new JTextField(100);
        userQuestionText.setBounds(150, 20, 165, 25);
        panel.add(userQuestionText);
    }

    public void answerTool() {
        answerLabel = new JLabel("Enter answer:");
        answerLabel.setBounds(10, 50, 200, 25);
        panel.add(answerLabel);

        userAnswerText = new JTextField(100);
        userAnswerText.setBounds(150, 50, 165, 25);
        panel.add(userAnswerText);
    }

    public void submitTool() {
        submitButton = new JButton("Submit");
        submitButton.setBounds(120, 100, 80, 25);
        submitButton.addActionListener(this);
        panel.add(submitButton);
    }

    public static void main(String[] args) {
        new GraphicalDesignFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String question = userQuestionText.getText();
            String answer = userAnswerText.getText();
            questionBank.addQuestion(question, answer);

            Object[] options = {"No, only for this game", "Save forever"};
            int optionDialog = JOptionPane.showOptionDialog(frame,
                    "Your question is: " + "\""
                            + question + "\"."
                            + " \n The answer is " + "\"" + answer + "\""
                            + ". \n It has been successfully added. \n Save it to question bank forever?",
                    "Forever and ever?",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[1]);

            if (optionDialog == JOptionPane.NO_OPTION) {
                jsonWriter.open();
                jsonWriter.write(questionBank);
                jsonWriter.close();
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }

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

}

