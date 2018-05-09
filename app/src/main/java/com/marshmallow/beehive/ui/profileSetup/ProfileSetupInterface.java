package com.marshmallow.beehive.ui.profileSetup;

/**
 * Created by George on 5/8/2018.
 */
public interface ProfileSetupInterface {

    /**
     * Load in saved data from model when the activity starts
     */
    void loadProfileData();

    /**
     * Save the profile data entered on screen for processing
     */
    void saveProfileData();

    /**
     * @return true if all necessary fields in the view have been filled out
     */
    Boolean allFieldsComplete();
}
