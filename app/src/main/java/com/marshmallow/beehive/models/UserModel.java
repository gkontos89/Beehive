package com.marshmallow.beehive.models;

/**
 * This model is for a user account
 *
 * Created by George on 4/8/2018.
 */
public class UserModel {
    private String userName;
    private int profilePicture;
    private StoryModel userStory;
    private String quickPitch;

    public UserModel() {
        userStory = new StoryModel();
    }

    /**
     * Getters and setters
     */
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public int getProfilePicture() { return profilePicture; }
    // TODO update setter for profile picture
    public void setProfilePicture(int profilePicture) { this.profilePicture = profilePicture; }
    public StoryModel getUserStory() { return userStory; }
    public void setUserStory(StoryModel userStory) { this.userStory = userStory; }
    public String getQuickPitch() { return quickPitch; }
    public void setQuickPitch(String quickPitch) { this.quickPitch = quickPitch; }
}


