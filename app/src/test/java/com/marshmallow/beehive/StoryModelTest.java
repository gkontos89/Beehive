package com.marshmallow.beehive;

import com.marshmallow.beehive.models.CareerPointModel;
import com.marshmallow.beehive.models.StoryModel;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.List;
import java.util.Vector;

/**
 * Created by George on 5/18/2018.
 */
public class StoryModelTest {
    private StoryModel storyModel = new StoryModel();

    @Test
    public void testConstructor() {
        assertThat(storyModel.getCareerPointModels(), instanceOf(Vector.class));
        assertThat(storyModel.getPursuits(), instanceOf(Vector.class));
        assertThat(storyModel.getStoryQuestionModels(), instanceOf(Vector.class));
    }

    @Test
    public void testSummary() {
        String summary = "testSummary";
        storyModel.setSummary(summary);
        assertEquals(storyModel.getSummary(), summary);
    }

}
