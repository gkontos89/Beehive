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

    private static ProfileSetupManager instance = null;
    private Vector<Class> profileSetupTransitions;
    private ListIterator profileSetupTransitionIterator;

    // TODO handle sending of profile data to backend

    private ProfileSetupManager() {
        profileSetupTransitions = new Vector<>();
        profileSetupTransitionIterator = profileSetupTransitions.listIterator();

        // TODO add the home screen activity as the final activity
        profileSetupTransitions.add(ProfileSetupBasicsActivity.class);
        profileSetupTransitions.add(ProfileSetupSummaryActivity.class);
        profileSetupTransitions.add(ProfileSetupCareerActivity.class);
    }

    public static ProfileSetupManager getInstance() {
        if (instance == null) {
            instance = new ProfileSetupManager();
        }

        return instance;
    }

    public Class getNextProfileSetupActivity() {
        if (profileSetupTransitionIterator.hasNext()) {
            return profileSetupTransitionIterator.next().getClass();
        } else {
            return null;
        }
    }

    public Class getLastProfileSetupActivity() {
        if (profileSetupTransitionIterator.hasPrevious()) {
            return profileSetupTransitionIterator.previous().getClass();
        } else {
            return null;
        }
    }
}
