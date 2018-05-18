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
    private String accountId;
    private CareerPointModel activeCareerPointModel = null;
    private CareerPositionModel activeCareerPointPositionModel = null;

    private ModelManager() {
        userModel = new UserModel();
    }

    public static ModelManager getInstance() {
        if (instance == null) {
            instance = new ModelManager();
        }

        return instance;
    }

    public UserModel getUserModel() { return userModel; }
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

    // The following methods will deal with CareerPoint and CareerPosition model handling for user interaction and updates

    /**
     * This method will generate a new career point model and set it as the active one.
     */
    public void generateNewCareerPointModel() {
        activeCareerPointModel = new CareerPointModel();
    }

    /**
     * This method will set the model in the manager to the model passed in
     */
    public void setActiveCareerPointModel(CareerPointModel careerPointModel) {
        activeCareerPointModel = careerPointModel;
    }

    /**
     * This method simply returns the activeCareerPointModel
     */
    public CareerPointModel getActiveCareerPointModel() { return activeCareerPointModel; }

    /**
     * This method will remove the activeCareerPoint model from the user profile
     */
    public void removeActiveCareerPointModel() {
        getUserModel().getUserStory().getCareerPointModels().remove(activeCareerPointModel);
        activeCareerPointModel = null;
    }

    /**
     * This method will save the current active model into the users story
     */
    public void saveActiveCareerPointModel() {
        if (!getUserModel().getUserStory().getCareerPointModels().contains(activeCareerPointModel)) {
            CareerPointModel careerPointModel = activeCareerPointModel;
            getUserModel().getUserStory().getCareerPointModels().add(careerPointModel);
        }

        activeCareerPointModel = null;
    }

    /**
     * Checks that the activeCareerPointModel is contained in the user story of the main user.
     * Handy for profile updates and screen switching to know when to save data
     */
    public Boolean userStoryContainsActiveCareerPointModel() {
        return getUserModel().getUserStory().getCareerPointModels().contains(activeCareerPointModel);
    }

    /**
     * This method will generate a new position model
     */
    public void generateNewCareerPointPositionModel() {
        activeCareerPointPositionModel = new CareerPositionModel();
    }

    /**
     * sets the active careerpoint position model
     * @param careerPointPositionModel - position model that will be active
     */
    public void setActiveCareerPointPositionModel(CareerPositionModel careerPointPositionModel) {
        activeCareerPointPositionModel = careerPointPositionModel;
    }

    /**
     * simply return the active position model being updated
     * @return the active career point position model being operated on
     */
    public CareerPositionModel getActiveCareerPointPositionModel() {
        return activeCareerPointPositionModel;
    }

    /**
     * removes the active position model
     */
    public void removeActiveCareerPositionModel() {
        activeCareerPointModel.getCareerPositionModels().remove(activeCareerPointPositionModel);
        activeCareerPointPositionModel = null;
    }

    /**
     * saves the active career point position model into the active career point model
     */
    public void saveActiveCareerPointPositionModel() {
        if (!getActiveCareerPointModel().getCareerPositionModels().contains(activeCareerPointPositionModel)) {
            CareerPositionModel careerPositionModel = activeCareerPointPositionModel;
            activeCareerPointModel.getCareerPositionModels().add(careerPositionModel);
        }

        activeCareerPointPositionModel = null;
    }
}
