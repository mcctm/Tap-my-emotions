# Tap my emotions

## A fun game to learn more about emotions!

### Introduction

**Tap my emotions** is an application designed for children on the Autism Spectrum 
to better understand and recognize their own and other's emotions through the game 
*"How would they feel?"*.

A simple run of the application is:

- Users choose if they want to be a **player** or **designer**.
- **Players**: Each time, a scenario will be presented (e.g. A friend broke your 
favourite toy, how would you feel?), and players will then type in what emotions they will experience 
in that scenario. They will be given feedback of their total score.
- **Designers**: Designers add new scenarios to the game. They will first write 
out the scenario, and write down the correct answer.

Target audience:
- Children facing difficulties in identifying emotions will be the **players** 
of the application. 
- Teachers or parents will act as the **designers** and can modify or add new 
scenarios to the existing game.

Why?

My experience working as a therapist for elementary school children on the Autism Spectrum 
sparked my interest in this project. Many of my students found it challenging to express 
themselves emotionally because they do not understand the variety of emotions one can feel.

### User Stories

- As a user, I want to be able to choose if I am the designer or player.
- As a user (player), I want to be able to view question prompts from the question bank, one at a time.
- As a user (player), I want to be able to type in my answer for the given question.
- As a user (player), I want to see my score after I have completed the game, and receive corresponding feedback.
- As a user (designer), I want to be able to add multiple new questions to the question bank.
- As a user (designer), I want to be able to save the newly added questions to the question bank file.
- As a user (both players and designers), I want to be able to load questions from the question bank file.

### Phase 4: Task 2
- I chose the option "Make appropriate use of the Map interface somewhere in your code."
- Class consisting the Map interface: GraphicalPlayFrame.
- Purpose: The map acts as a vocabulary bank for the multiple choice questions.
           It stores the emotional words in a list with their first letter as the key.
           
### Phase 4: Task 3 
(1) Improve gui frame classes
- GraphicalDesignFrame, GraphicalHomeFrame, GraphicalPlayFrame are all associated with the same classes
(QuestionBank, JsonReader, JsonWriter).
 - Highly similar design, resulting in a lot of coupling and repetitive code, making it more error-prone.
 
 
 To refactor this:
- Create an abstract class called GraphicalFrame in the ui package.
- Includes methods that all the three classes need (e.g. frame/panel colour, initialize json files if prompted).

(2) Improve QuestionBank design
- Right now, it is in the form of arraylist, and all questions are associated with a number. Therefore, to get
a question we will have to get it from its position, then access necessary fields to perform for-loops.


To refactor this:
- Let QuestionBank implement Iterable interface, so that foreach loops can be applied directly to the question elements
in the QuestionBank class.
  