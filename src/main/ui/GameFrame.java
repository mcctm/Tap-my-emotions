package ui;

import model.QuestionBank;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Referenced TellerApp for ui and JsonSerializationDemo for saving and loading of files
// The user interface of the Application
public class GameFrame {

    private static final String JSON_STORE = "./data/questionbank.json";
    private QuestionBank questionBank;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the game application
    public GameFrame() {
        runGameFrame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGameFrame() {
        Scanner input;
        boolean keepGoing = true;
        String command;

        input = new Scanner(System.in);
        questionBank = new QuestionBank();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Thanks for playing! See you again soon!");
    }


    // EFFECTS: displays menu for users to pick if they are player or designer
    private void displayMenu() {
        System.out.println("\nAre you a player or designer?");
        System.out.println("\tp -> player");
        System.out.println("\td -> designer");
        System.out.println("\ts -> save questions to question bank");
        System.out.println("\tl -> load questions from question bank");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command on whether they are player or designer
    private void processCommand(String command) {
        if (command.equals("p")) {
            startGame();
        } else if (command.equals("d")) {
            startDesign();
        } else if (command.equals("s")) {
            saveQuestionBank();
        } else if (command.equals("l")) {
            loadQuestionBank();
        } else {
            System.out.println("Selection not valid. Try again!");
        }
    }

    // MODIFIES: this
    // EFFECTS: Starts game for players to play, calculates their score at the end
    public void startGame() {

        int score = 0;
        Scanner playerInput = new Scanner(System.in);

        int numberOfQuestions = questionBank.listAllQuestionsInListOfString().size() / 2;

        if (numberOfQuestions == 0) {
            System.out.println("No questions yet... Do you want to load some questions from the question bank? :)");
        } else {
            for (int i = 0; i < numberOfQuestions; i++) {

                System.out.println(questionBank.getQuestionPrompt(i)
                        + "\n Choices: " + questionBank.getQuestionAnswer(i) + " / " + "angry"
                        + " / " + "nervous");

                String playerAnswer = playerInput.nextLine();
                playerAnswer = playerAnswer.toLowerCase();

                if (questionBank.checkAnswer(playerAnswer, i)) {
                    score++;
                    System.out.println("That's right! The correct answer was "
                            + "\"" + questionBank.getQuestionAnswer(i) + "\"" + ". Keep it up!");
                } else {
                    System.out.println("Incorrect answer. The correct answer was "
                            + "\"" + questionBank.getQuestionAnswer(i) + "\"" + ". Keep going!");
                }

            }
            scoreFeedback(score);
        }
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

    // MODIFIES: this
    // EFFECTS: Starts application for designers to input new questions
    public void startDesign() {
        try {
            Scanner designerInput = new Scanner(System.in);
            questionBank = jsonReader.read();

            System.out.println("Enter question:");
            String designerQuestion = designerInput.nextLine();

            System.out.println("Enter answer:");
            String designerAnswer = designerInput.nextLine();
            designerAnswer = designerAnswer.toLowerCase();

            System.out.println("Your question is: " + "\"" + designerQuestion + "\""
                    + " and the answer is " + "\"" + designerAnswer + "\"");

            questionBank.addQuestion(designerQuestion, designerAnswer);

            System.out.println("Successfully entered. Returning to homepage.");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

    }

    // EFFECTS: saves the question bank to file
    private void saveQuestionBank() {
        try {
            if (questionBank.getQuestionSetInListOfQuestion().size() == 0) {
                System.out.println("Question bank not saved as there are no new questions added.");
            } else {
                jsonWriter.open();
                jsonWriter.write(questionBank);
                jsonWriter.close();
                System.out.println("Saved questions to" + JSON_STORE);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads question bank from file
    private void loadQuestionBank() {
        try {
            questionBank = jsonReader.read();
            System.out.println("Loaded questions from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}
