package ui;

import model.QuestionBank;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

public class GraphicalHomeFrame extends JFrame implements ActionListener {

    protected static final String JSON_STORE = "./data/questionbank.json";
    protected QuestionBank questionBank;
    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;

    protected JFrame frame;
    protected JPanel panel;
    protected JLabel title;
    private JLabel successfullyLoadedMessage;

    public GraphicalHomeFrame() {
        frame = new JFrame();

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(100, 30, 120, 30));
        panel.setLayout(new GridLayout(5, 5));
        panel.setBackground(Color.getHSBColor(60, 0, 50));

        title = new JLabel("♡ Tap my heart ♡");
        title.setBounds(150, 0, 1000, 100);
        title.setFont(new Font("Noto Sans", Font.PLAIN, 30));
        frame.add(title);

        ImageIcon imgIcon = new ImageIcon("./data/emotions.png");
        Image image = imgIcon.getImage();
        Image imgScale = image.getScaledInstance(500, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        JLabel imageLabel = new JLabel(scaledIcon);
        panel.add(imageLabel);

        playGameAndDesignGameTools();
        loadGameTool();

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tap my heart");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GraphicalHomeFrame();
    }

    // MODIFIES: this
    // EFFECTS:  a helper method which declares and instantiates Play game and Design new game tools
    private void playGameAndDesignGameTools() {

        JButton playGameButton = new JButton("Play game");
        playGameButton.addActionListener(e -> new GraphicalPlayFrame());
        panel.add(playGameButton);

        JButton designGameButton = new JButton("Design new questions");
        designGameButton.addActionListener(e -> new GraphicalDesignFrame());
        panel.add(designGameButton);

    }

    // MODIFIES: this
    // EFFECTS:  a helper method which declares and instantiates tools to load and save game
    private void loadGameTool() {

        JButton loadQuestionsButton = new JButton("Load question bank");
        loadQuestionsButton.addActionListener(this);
        panel.add(loadQuestionsButton);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        init();
        System.out.println("Loaded questions from " + JSON_STORE);
        JOptionPane.showMessageDialog(frame, "Successfully loaded the questions.");
    }

}