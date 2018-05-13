package com.marshmallow.beehive.models;

import org.json.JSONObject;

/**
 * The model manager contains the data models and methods for retrieving and updating them
 *
 * Created by George on 4/8/2018.
 */
public class ModelManager {
    private static ModelManager instance = null;
    private UserModel userModel;
    private CareerPointModel tempCareerPointModel = null;

    private ModelManager() {
        userModel = new UserModel();
    }

    public static ModelManager getInstance() {
        if (instance == null) {
            instance = new ModelManager();
        }
        return instance;
    }

    /**
     * This method will simply return the model in the controller
     */
     public UserModel getUserModel() { return userModel; }

    /**
     * This method will generate a new temporary CareerPointModel used for updating profiles
     */
    public CareerPointModel generateCareerPointModel() {
        tempCareerPointModel = new CareerPointModel();
        return tempCareerPointModel;
    }

    /**
     * This method simply returns the tempCareerPointModel
     */
    public CareerPointModel getTempCareerPointModel() { return tempCareerPointModel; }
}
