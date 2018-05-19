package com.marshmallow.beehive;

import com.marshmallow.beehive.models.StoryQuestionModel;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by George on 5/18/2018.
 */
public class StoryQuestionModelTest {
    private StoryQuestionModel storyQuestionModel = new StoryQuestionModel();

    @Test
    public void testStoryQuestion() {
        String question = "testQuestion";
        String answer = "testAnswer";
        storyQuestionModel.setQuestion(question);
        storyQuestionModel.setAnswer(answer);
        assertEquals(storyQuestionModel.getQuestion(), question);
        assertEquals(storyQuestionModel.getAnswer(), answer);
    }

}
