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

    private static final String JSON_STORE = "./data/questionbank.json";
    private QuestionBank questionBank;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel borderPanel;
    private JLabel title;
    private ImageIcon imgInIcon;
    private JLabel image;

    public GraphicalHomeFrame() {
        frame = new JFrame();
        frame.setSize(800, 800);

        borderPanel();
        mainPanel();
        title();
        mainPanel.add(title);
        image();
        mainPanel.add(image);

        allButtonTools();

        borderPanel.add(mainPanel, new GridBagConstraints());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tap my heart");
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GraphicalHomeFrame();
    }

    // MODIFIES: this
    // EFFECTS: declares and instantiates the title on homepage
    private void title() {
        title = new JLabel("♡ Tap my heart ♡");
        title.setFont(new Font("Noto Sans", Font.PLAIN, 50));
        title.setBorder(BorderFactory.createEmptyBorder(0, 70, 20, 0));
    }

    // MODIFIES: this
    // EFFECTS: declares and instantiates the image on homepage
    private void image() {
        // Picture from https://blog.ubicare.com/driving-patient-loyalty-by-connecting-outside-the-hospital-early-on
        imgInIcon = new ImageIcon("./data/emotions.png");
        image = new JLabel(imgInIcon);
        image.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
    }

    // MODIFIES: this
    // EFFECTS: declares and instantiates main panel containing all visible labels and buttons
    private void mainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(Color.getHSBColor(60, 0, 30));
    }

    // MODIFIES: this
    // EFFECTS: declares and instantiates the image on homepage
    private void borderPanel() {
        borderPanel = new JPanel(new BorderLayout());
        frame.add(borderPanel);
        borderPanel.setBackground(Color.getHSBColor(60, 0, 30));
        borderPanel.setLayout(new GridBagLayout());
    }

    // MODIFIES: this
    // EFFECTS: declares and instantiates all buttons on homepage
    private void allButtonTools() {

        JButton playGameButton = new JButton("Play game");
        playGameButton.addActionListener(e -> new GraphicalPlayFrame());
        mainPanel.add(playGameButton);

        JButton designGameButton = new JButton("Design new questions");
        designGameButton.addActionListener(e -> new GraphicalDesignFrame());
        mainPanel.add(designGameButton);

        JButton loadQuestionsButton = new JButton("Load question bank");
        loadQuestionsButton.addActionListener(this);
        mainPanel.add(loadQuestionsButton);

    }

    // MODIFIES: this
    // EFFECTS: initializes questionBank and loads the saved data from json
    @Override
    public void actionPerformed(ActionEvent e) {
        init();
        System.out.println("Loaded questions from " + JSON_STORE);
        JOptionPane.showMessageDialog(frame, "Successfully loaded the questions.");
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

}