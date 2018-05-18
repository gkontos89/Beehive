package com.marshmallow.beehive.backendCommunications.backends;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.marshmallow.beehive.backendCommunications.webServices.CreateUserWebService;
import com.marshmallow.beehive.backendCommunications.webServices.UpdateProfileWebService;

/**
 * Created by George on 5/15/2018.
 */
public class GoBackend implements BeehiveBackendInterface {

    private Boolean userSignedIn = false;
    private String sessionId = null;

    public GoBackend() {
    }

    @Override
    public Boolean isUserSignedIn() {
        return userSignedIn;
    }

    @Override
    public void createUserWithEmailAndPassword(Context context, Activity activity, String email, String password) {
        Intent intent = new Intent(context, CreateUserWebService.class);
        intent.putExtra(CreateUserWebService.emailKey, email);
        intent.putExtra(CreateUserWebService.passwordKey, password);
        context.startService(intent);
    }

    @Override
    public void signInWithEmailAndPassword(Context context, Activity activity, String email, String password) {

    }

    @Override
    public void signOutUser() {

    }

    @Override
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
        userSignedIn = true;
    }

    @Override
    public String getSessionId() { return sessionId; }

    @Override
    public void submitUserProfileUpdates(Context context) {
        Intent intent = new Intent(context, UpdateProfileWebService.class);
        context.startService(intent);
    }
}
