package com.marshmallow.beehive.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by George on 4/8/2018.
 */
public class StoryQuestionModel {

    private String question;
    private String answer;

    public StoryQuestionModel() {
    }

    /**
     * Getters and setters
     */
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
}
