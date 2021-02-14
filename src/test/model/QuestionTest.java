package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    public void testAddQuestionOne() {

        QuestionBank questionBank = new QuestionBank();

        String designerQuestion = "Mary lost her favourite pen. How would she feel?";
        String designerAnswer = "Sad";
        questionBank.addQuestion(designerQuestion, designerAnswer);

        List<String> result = questionBank.listAllQuestions();

        assertTrue(result.contains("Mary lost her favourite pen. How would she feel?"));
        assertTrue(result.contains("Sad"));
        assertEquals(2, result.size());

    }

    @Test
    public void testAddQuestionMultiple() {

        QuestionBank questionBank = new QuestionBank();

        String designerQuestion = "Mary lost her favourite pen. How would she feel?";
        String designerAnswer = "Sad";
        questionBank.addQuestion(designerQuestion, designerAnswer);

        String designerQuestion2 = "Tom's mother gave him a present for his birthday. How would he feel?";
        String designerAnswer2 = "Happy";
        questionBank.addQuestion(designerQuestion2, designerAnswer2);

        List<String> result = questionBank.listAllQuestions();

        assertTrue(result.contains("Mary lost her favourite pen. How would she feel?"));
        assertTrue(result.contains("Tom's mother gave him a present for his birthday. How would he feel?"));

        assertTrue(result.contains("Happy"));
        assertTrue(result.contains("Sad"));
        assertEquals(4, result.size());

    }

    @Test
    public void testCheckAnswer() {

        QuestionBank questionBank = new QuestionBank();

        String playerQuestion = "Susan worked for the whole day. How would she feel?";
        String playerAnswer = "Tired";
        questionBank.addQuestion(playerQuestion, playerAnswer);

        String playerQuestion2 = "Someone broke your favourite cup. How would you feel?";
        String playerAnswer2 = "Angry";
        questionBank.addQuestion(playerQuestion2, playerAnswer2);

        assertTrue(questionBank.checkAnswer(playerAnswer, 0));
        assertFalse(questionBank.checkAnswer(playerAnswer2, 0));

        assertTrue(questionBank.checkAnswer(playerAnswer2, 1));
        assertFalse(questionBank.checkAnswer(playerAnswer, 1));

    }


}