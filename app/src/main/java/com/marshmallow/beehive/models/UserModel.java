package com.marshmallow.beehive.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.google.firebase.database.Exclude;

import java.io.ByteArrayOutputStream;

/**
 * This model is for a user account
 *
 * Created by George on 4/8/2018.
 */
public class UserModel {
    private String accountId;
    private String userName;
    private String profilePicture;
    private StoryModel userStory;
    private String quickPitch;
    private String email;

    public UserModel() {
    }

    /**
     * Getters and setters
     */
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getProfilePicture() { return profilePicture; }
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
    public StoryModel getUserStory() {
        if (userStory == null) {
            userStory = new StoryModel();
        }
        return userStory;
    }
    public void setUserStory(StoryModel userStory) { this.userStory = userStory; }
    public String getQuickPitch() { return quickPitch; }
    public void setQuickPitch(String quickPitch) { this.quickPitch = quickPitch; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Exclude
    public Bitmap getProfilePictureBitmap() {
        if (profilePicture != null) {
            byte[] decodedString = Base64.decode(profilePicture, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        } else {
            return  null;
        }
    }

    @Exclude
    public void storeProfilePictureFromBitmap(Bitmap profilePictureBitmap) {
        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
        profilePictureBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayBitmapStream);
        byte[] b = byteArrayBitmapStream.toByteArray();
        profilePicture = Base64.encodeToString(b, Base64.DEFAULT);
    }
}



