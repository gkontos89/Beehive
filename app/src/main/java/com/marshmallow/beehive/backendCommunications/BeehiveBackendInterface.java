package com.marshmallow.beehive.backendCommunications;

import android.app.Activity;
import android.content.Context;

/**
 * This interface establishes the contract for all backend communications for Beehive so that
 * different backends can be swapped out as needed
 *
 * Created by George on 4/29/2018.
 */
public interface BeehiveBackendInterface {

    /**
     * Determines if a user is already signed into the application
     * @return true if user is currently signed in
     */
    Boolean isUserSignedIn();

    /**
     * Attempts to create a new user with email and password credentials
     */
    void createUserWithEmailAndPassword(Context context, Activity activity, String email, String password);

    /**
     * Attempts to sign in to an existing account using email and password credentials
     */
    void signInWithEmailAndPassword(Context context, Activity activity, String email, String password);

    /**
     * Signs out the current user from the back end services
     */
    void signOutUser();
}
