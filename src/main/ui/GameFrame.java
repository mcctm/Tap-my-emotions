package ui;

import model.QuestionBank;

import java.util.ArrayList;

import java.util.Scanner;

// Referenced TellerApp
public class GameFrame {

    private Scanner input;
    private QuestionBank questionBank;

    // EFFECTS: runs the game application
    public GameFrame() {
        runGameFrame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGameFrame() {
        boolean keepGoing = true;
        String command = null;

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

        System.out.println("\nGoodbye!");
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


    // REQUIRES: questionBank contains at least one Question element
    // EFFECTS: Starts game for players to play, with score displayed at the end
    public void startGame() {

        int score = 0;
        Scanner playerInput = new Scanner(System.in);

        int numberOfQuestions = questionBank.listAllQuestions().size() / 2;

        for (int i = 0; i < numberOfQuestions; i++) {

            System.out.println(questionBank.getQuestionPrompt(i));

            String playerAnswer = playerInput.nextLine();
            String correctAnswer = questionBank.getQuestionAnswer(i);

            if (playerAnswer.equals(correctAnswer)) {
                score++;
            }

        }
        System.out.println("You got " + score + " / " + numberOfQuestions + " questions. Keep it up!");

    }

    // MODIFIES: questionBank
    // EFFECTS: Starts application for designers to input new questions
    public void startDesign() {

        Scanner designerInput = new Scanner(System.in);
        String selection = "";

        System.out.println("Enter question:");
        String designerQuestion = designerInput.nextLine();
        System.out.println("Enter answer:");
        String designerAnswer = designerInput.nextLine();

        System.out.println("Your question is: " + "\"" + designerQuestion + "\""
                + " and the answer is " + "\"" + designerAnswer + "\"");

        questionBank.addQuestion(designerQuestion, designerAnswer);

        System.out.println("Successfully entered. Returning to homepage.");


    }


}
