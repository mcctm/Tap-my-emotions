package ui;

import model.QuestionBank;

import java.util.Scanner;

// Referenced TellerApp
// The user interface of the Application
public class GameFrame {

    private QuestionBank questionBank;

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
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command on whether they are player or designer
    private void processCommand(String command) {
        if (command.equals("p")) {
            startGame();
        } else if (command.equals("d")) {
            startDesign();
        } else {
            System.out.println("Selection not valid. Try again!");
        }
    }

    // MODIFIES: this
    // EFFECTS: Starts game for players to play, calculates their score at the end
    public void startGame() {

        int score = 0;
        Scanner playerInput = new Scanner(System.in);

        int numberOfQuestions = questionBank.listAllQuestions().size() / 2;

        if (numberOfQuestions == 0) {
            System.out.println("No questions yet, see if anyone can input some questions for you to play? :)");
        } else {
            for (int i = 0; i < numberOfQuestions; i++) {

                System.out.println(questionBank.getQuestionPrompt(i)
                        + "\n Choices: " + questionBank.getQuestionAnswer(i) + " / " + "angry"
                        + " / " + "nervous");

                String playerAnswer = playerInput.nextLine();
                playerAnswer = playerAnswer.toLowerCase();

                if (questionBank.checkAnswer(playerAnswer, i)) {
                    score++;
                }

            }
            scoreFeedback(score);
        }
    }

    // EFFECTS: Provide different feedbacks based on player's score
    public void scoreFeedback(int score) {

        int numberOfQuestions = questionBank.listAllQuestions().size() / 2;

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

        Scanner designerInput = new Scanner(System.in);

        System.out.println("Enter question:");
        String designerQuestion = designerInput.nextLine();

        System.out.println("Enter answer:");
        String designerAnswer = designerInput.nextLine();
        designerAnswer = designerAnswer.toLowerCase();

        System.out.println("Your question is: " + "\"" + designerQuestion + "\""
                + " and the answer is " + "\"" + designerAnswer + "\"");

        questionBank.addQuestion(designerQuestion, designerAnswer);

        System.out.println("Successfully entered. Returning to homepage.");


    }


}
