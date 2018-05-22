package com.marshmallow.beehive;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.marshmallow.beehive.ui.profileSetup.ProfileSetupCareerActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by George on 5/21/2018.
 */

@RunWith(AndroidJUnit4.class)
public class ProfileSetupCareerTest {

    @Rule
    public ActivityTestRule<ProfileSetupCareerActivity> profileSetupCareerActivityActivityTestRule = new ActivityTestRule<>(ProfileSetupCareerActivity.class);

    @Test
    public void testAddCareerPointWithoutCareerPositions() {
        onView(withId(R.id.add_career_point_button)).perform(click());

        // Individual Career Point screen
        onView(withId(R.id.career_point_text)).perform(typeText("Caterpillar"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.career_point_location_text)).perform(typeText("Peoria, IL"), closeSoftKeyboard());
        onView(withId(R.id.start_date_text)).perform(typeText("6/5/2011"), closeSoftKeyboard());
        onView(withId(R.id.end_date_text)).perform(typeText("7/3/2014)"), closeSoftKeyboard());
    }

}
