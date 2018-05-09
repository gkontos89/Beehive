package com.marshmallow.beehive.ui.profileSetup;

import android.app.Activity;

import com.marshmallow.beehive.ui.home.HomeActivity;

import java.util.ListIterator;
import java.util.Vector;

/**
 * This class handles the transitions for profile setup screens by holding the state of the current
 * setup and determining what comes next
 *
 * Created by George on 5/7/2018.
 */
public class ProfileSetupManager extends ControlFlow{

    private static ProfileSetupManager instance = null;
    private Vector<Class> profileSetupTransitions;
    private ListIterator profileSetupTransitionIterator;

    // TODO handle sending of profile data to backend

    private ProfileSetupManager() {
        profileSetupTransitions = new Vector<>();
        profileSetupTransitionIterator = profileSetupTransitions.listIterator();

        // Add transitions
        profileSetupTransitions.add(ProfileSetupBasicsActivity);
        profileSetupTransitions.add(ProfileSetupSummaryActivity.class);
        profileSetupTransitions.add(ProfileSetupCareerActivity.class);
        profileSetupTransitions.add(HomeActivity.class);
    }

    public static ProfileSetupManager getInstance() {
        if (instance == null) {
            instance = new ProfileSetupManager();
        }

        return instance;
    }

    public Class getNextProfileSetupActivity() {
        if (profileSetupTransitionIterator.hasNext()) {
            return (Class) profileSetupTransitionIterator.next();
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
