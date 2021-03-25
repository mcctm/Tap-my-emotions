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

public class GraphicalDesignFrame extends GraphicalHomeFrame implements ActionListener {

    private JLabel questionLabel;
    private JLabel answerLabel;
    private JTextField userQuestionText;
    private JTextField userAnswerText;
    private JButton submitButton;
    private JLabel addedToQuestionBank;

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
        addedToQuestionBankMessage();

        frame.setVisible(true);

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

    public void addedToQuestionBankMessage() {
        addedToQuestionBank = new JLabel("");
        addedToQuestionBank.setBounds(50, 180, 1000, 100);
        panel.add(addedToQuestionBank);
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
            addedToQuestionBank.setText("Your question is: " + "\""
                    + question + "\""
                    + " and the answer is " + "\"" + answer + "\""
                    + ". It has been successfully added.");
            jsonWriter.open();
            jsonWriter.write(questionBank);
            jsonWriter.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }

}

