package com.marshmallow.beehive.ui.profileSetup;

import android.app.Activity;

import java.util.ListIterator;
import java.util.Vector;

/**
 * This class handles the transitions for profile setup screens by holding the state of the current
 * setup and determining what comes next
 *
 * Created by George on 5/7/2018.
 */
public class ProfileSetupManager {

    private static ProfileSetupManager instance;
    private Vector<Activity> profileSetupTransitions;
    private ListIterator profileSetupTransitionIterator;

    // TODO hold temporary profile setup data here then map to user model at the end

    private ProfileSetupManager() {
        profileSetupTransitions = new Vector<>();
        profileSetupTransitionIterator = profileSetupTransitions.listIterator();

        // TODO fill out transitions
    }

    public ProfileSetupManager getInstance() {
        if (instance == null) {
            instance = new ProfileSetupManager();
        }

        return instance;
    }

    public Activity getNextProfileSetupActivity() {
        if (profileSetupTransitionIterator.hasNext()) {
            return (Activity) profileSetupTransitionIterator.next();
        } else {
            return null;
        }
    }

    public Activity getLastProfileSetupActivity() {
        if (profileSetupTransitionIterator.hasPrevious()) {
            return (Activity) profileSetupTransitionIterator.previous();
        } else {
            return null;
        }
    }
}
