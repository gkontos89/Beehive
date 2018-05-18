package com.marshmallow.beehive.backendCommunications.backends;

import android.app.Activity;
import android.content.Context;

/**
 * BeehiveBackend is the front facing API's that are used throughout the application.
 * It implements the BeehiveBackendInterface so that all different backend models can be dropped in
 * and instantiated as the new backend model
 *
 * Created by George on 4/29/2018.
 */
public class BeehiveBackend implements BeehiveBackendInterface {
    private static BeehiveBackend instance = null;
//    private FirebaseBackend backendHandle;
    private GoBackend backendHandle;

    private BeehiveBackend() {
//        backendHandle = new FirebaseBackend();
        backendHandle = new GoBackend();
    }

    public static BeehiveBackend getInstance() {
        if (instance == null) {
            instance = new BeehiveBackend();
        }

        return instance;
    }

    @Override
    public Boolean isUserSignedIn() {
        return backendHandle.isUserSignedIn();
    }

    @Override
    public void createUserWithEmailAndPassword(Context context, Activity activity, String email, String password) {
        backendHandle.createUserWithEmailAndPassword(context, activity, email, password);
    }

    @Override
    public void signInWithEmailAndPassword(Context context, Activity activity, String email, String password) {
        backendHandle.signInWithEmailAndPassword(context, activity, email, password);
    }

    @Override
    public void signOutUser() {
        backendHandle.signOutUser();
    }

    @Override
    public void setAccountIds(String resourceId, String sessionId) {
        backendHandle.setAccountIds(resourceId, sessionId);
    }

    @Override
    public String getResourceId() { return backendHandle.getResourceId(); }

    @Override
    public String getSessionId() { return backendHandle.getSessionId(); }


}
