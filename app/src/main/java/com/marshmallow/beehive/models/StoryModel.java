package com.marshmallow.beehive.models;

import java.util.Vector;

/**
 * This model represents a user's story, which is essentially a resume.  Here we can customize and
 * keep adding/changing/removing fields that we find to be better as we go along
 *
 * Created by George on 4/8/2018.
 */
public class StoryModel {
    private String summary;
    private Vector<CareerPointModel> careerPointModels;
    private Vector<String> pursuits;
    private Vector<StoryQuestionModel> storyQuestionModels;

    public StoryModel() {

    }

    /**
     * Getters and setters
     */
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public Vector<CareerPointModel> getCareerPointModels() { return careerPointModels; }
    public Vector<String> getPursuits() { return pursuits; }
    public Vector<StoryQuestionModel> getStoryQuestionModels() { return storyQuestionModels; }
}
