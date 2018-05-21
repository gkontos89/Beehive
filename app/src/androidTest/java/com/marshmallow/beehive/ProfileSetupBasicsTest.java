package com.marshmallow.beehive;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import static org.junit.Assert.*;

import com.marshmallow.beehive.models.ModelManager;
import com.marshmallow.beehive.ui.profileSetup.ProfileSetupBasicsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 *
 * Created by George on 5/19/2018.
 */

@RunWith(AndroidJUnit4.class)
public class ProfileSetupBasicsTest {
    @Rule
    public ActivityTestRule<ProfileSetupBasicsActivity> loginActivityActivityTestRule = new ActivityTestRule<>(ProfileSetupBasicsActivity.class);

    // TODO handle pictures

    @Test
    public void testFillInBasicsInfo() {
        onView(withId(R.id.profile_name_text)).perform(typeText("George Kontos"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.profile_quick_pitch_text)).perform(typeText("Big dreamer"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.next_button)).perform(click());
        assertEquals(ModelManager.getInstance().getUserModel().getUserName(), "George Kontos");
        assertEquals(ModelManager.getInstance().getUserModel().getQuickPitch(), "Big dreamer");
    }

    @Test
    public void testBasicInfoWithEmptyFields() {
        onView(withId(R.id.profile_name_text)).perform(closeSoftKeyboard());
        onView(withId(R.id.next_button)).perform(click());
        onView(withId(R.id.profile_name_text)).check(matches(hasErrorText("Profile name cannot be empty.")));
        onView(withId(R.id.profile_name_text)).perform(typeText("George Kontos"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.next_button)).perform(click());
        onView(withId(R.id.profile_quick_pitch_text)).check(matches(hasErrorText("Quick pitch cannot be empty.")));
        onView(withId(R.id.profile_quick_pitch_text)).perform(typeText("Big dreamer"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.next_button)).perform(click());
    }

}
