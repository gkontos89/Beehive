package com.marshmallow.beehive.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

/**
 * This model represents a position held at a particular point in a career
 *
 * Created by George on 4/8/2018.
 */
public class CareerPositionModel {
    private String positionName;
    private String startDate;
    private String endDate;
    private String summary;

    public CareerPositionModel() {
    }

    /**
     * Getters and setters
     */
    public String getPositionName() { return positionName; }
    public void setPositionName(String positionName) { this.positionName = positionName; }
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
}
