package com.marshmallow.beehive.backendCommunications;

import android.app.Activity;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;

/**
 * BeehiveBackend is the front facing API's that are used throughout the application.
 * It implements the BeehiveBackendInterface so that all different backend models can be dropped in
 * and instantiated as the new backend model
 *
 * Created by George on 4/29/2018.
 */
public class BeehiveBackend implements BeehiveBackendInterface {
    private static BeehiveBackend instance = null;
    private FirebaseBackend backendHandle;

    private BeehiveBackend() {
        backendHandle = new FirebaseBackend();
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
    public Boolean createUserWithEmailAndPassword(Activity activity, String email, String password) {
        return backendHandle.createUserWithEmailAndPassword(activity, email, password);
    }

    @Override
    public Boolean signInWithEmailAndPassword(Context context, String email, String password) {
        return backendHandle.signInWithEmailAndPassword(context, email, password);
    }

    public FirebaseAuth getFirebaseAuth() { return backendHandle.getFirebaseAuth(); }
}
