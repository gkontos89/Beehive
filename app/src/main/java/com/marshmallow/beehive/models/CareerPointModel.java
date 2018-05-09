package com.marshmallow.beehive.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

/**
 * This is essentially a point in time spent at a single company.  Cool thing here is we can experiment
 * with sections that haven't been explored yet
 *
 * Created by George on 4/8/2018.
 */
public class CareerPointModel {
    private String name;
    private String location;
    private String startDate;
    private String endDate;
    private Boolean currentJob;
    private Vector<CareerPositionModel> careerPositionModels;

    public CareerPointModel() {
    }

    /**
     * Getters and setters
     */
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    public Boolean getCurrentJob() { return currentJob; }
    public void setCurrentJob(Boolean isCurrentJob) { currentJob = isCurrentJob; }
    public Vector<CareerPositionModel> getCareerPositionModels() { return careerPositionModels; }
}
