package com.marshmallow.beehive.models;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * This model represents a user's story, which is essentially a resume.  Here we can customize and
 * keep adding/changing/removing fields that we find to be better as we go along
 *
 * Created by George on 4/8/2018.
 */
public class StoryModel {
    private String summary;
    private List<CareerPointModel> careerPointModels;
    private List<String> pursuits;
    private List<StoryQuestionModel> storyQuestionModels;

    public StoryModel() {
        careerPointModels = new Vector<>();
        pursuits = new Vector<>();
        storyQuestionModels = new Vector<>();
    }

    /**
     * Getters and setters
     */
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public List<CareerPointModel> getCareerPointModels() { return careerPointModels; }
    public List<String> getPursuits() { return pursuits; }
    public List<StoryQuestionModel> getStoryQuestionModels() { return storyQuestionModels; }
}
