package com.marshmallow.beehive.accountCreation;

import android.os.SystemClock;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.RecyclerViewMatcher;
import com.marshmallow.beehive.models.ModelManager;
import com.marshmallow.beehive.ui.login.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by George on 5/24/2018.
 */
@RunWith(AndroidJUnit4.class)
public class CreateAccountProfileSetupIntegrationTest {

    @Rule
    public ActivityTestRule<LoginActivity> createAccountProfileSetupIntegrationTestActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Test
    public void testCreateAccountAndProfileSetup() {
        // Enter in an email and password
        onView(ViewMatchers.withId(R.id.email_text)).perform(typeText("testPerson4@gmail.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password_text)).perform(typeText("password"), ViewActions.closeSoftKeyboard());

        // Click create account button
        onView(withId(R.id.create_account_button)).perform(click());

        // TODO: lame i know, use espresso idling resources next time
        SystemClock.sleep(5000);

        // Welcome screens
        onView(withId(R.id.setup_profile_button)).perform(click());

        // Fill in Profile Basics
        onView(withId(R.id.profile_name_text)).perform(closeSoftKeyboard());
        onView(withId(R.id.next_button)).perform(click());
        onView(withId(R.id.profile_name_text)).check(matches(hasErrorText("Profile name cannot be empty.")));
        onView(withId(R.id.profile_name_text)).perform(typeText("George Kontos"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.next_button)).perform(click());
        onView(withId(R.id.profile_quick_pitch_text)).check(matches(hasErrorText("Quick pitch cannot be empty.")));
        onView(withId(R.id.profile_quick_pitch_text)).perform(typeText("Big dreamer"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.next_button)).perform(click());

        // Fill in Profile Summary
        onView(withId(R.id.summary_text_entry)).perform(typeText("Looking for adventure"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.pursuit_text_entry)).perform(typeText("Hiking"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.add_pursuit_button)).perform(click());
        onView(withId(R.id.pursuit_text_entry)).perform(typeText("Boating"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.add_pursuit_button)).perform(click());
        onView(withId(R.id.pursuit_text_entry)).perform(typeText("Parasailing"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.add_pursuit_button)).perform(click());
        assertEquals(ModelManager.getInstance().getUserModel().getUserStory().getPursuits().size(), 3);
        onView(withRecyclerView(R.id.pursuits_list).atPosition(0)).check(matches(hasDescendant(withText("Hiking"))));
        onView(withRecyclerView(R.id.pursuits_list).atPosition(1)).check(matches(hasDescendant(withText("Boating"))));

        // TODO story questions
        // Save data and validate model storage
        onView(withId(R.id.next_button)).perform(click());
        assertEquals(ModelManager.getInstance().getUserModel().getUserStory().getSummary(), "Looking for adventure");
        assertEquals(ModelManager.getInstance().getUserModel().getUserStory().getPursuits().size(), 3);

        // Setup career
        onView(withId(R.id.add_career_point_button)).perform(click());

        // Individual Career Point screen
        CareerPointCreator careerPointCreator = new CareerPointCreator("Caterpillar", "Peoria, IL", "6/5/2011", "7/3/2015");
        careerPointCreator.fillView();

        // Add career position
        careerPointCreator.addCareerPosition();
        CareerPositionCreator careerPositionCreator = new CareerPositionCreator("Associate", "6/5/2011", "7/3/2014", "Code monkey");
        careerPositionCreator.fillView();
        careerPositionCreator.saveCareerPosition();

        // Add career position
        careerPointCreator.addCareerPosition();
        careerPositionCreator = new CareerPositionCreator("Engineer", "7/5/2014", "7/7/2015", "Code ninja");
        careerPositionCreator.fillView();
        careerPositionCreator.saveCareerPosition();

        careerPointCreator.addCareerPosition();
        careerPositionCreator = new CareerPositionCreator("Ops Engineer", "7/5/2014", "7/7/2015", "Code ninja");
        careerPositionCreator.fillView();
        careerPositionCreator.saveCareerPosition();

        // Save career point
        careerPointCreator.saveCareerPoint();

        // Repeat 2 more times and save
        onView(withId(R.id.add_career_point_button)).perform(click());

        // Individual Career Point screen
        careerPointCreator = new CareerPointCreator("SPX", "Elk Grove, IL", "6/5/2016", "7/3/2017");
        careerPointCreator.fillView();

        // Add career position
        careerPointCreator.addCareerPosition();
        careerPositionCreator = new CareerPositionCreator("Entry", "6/5/2016", "8/19/2016", "Warrior coder");
        careerPositionCreator.fillView();
        careerPositionCreator.saveCareerPosition();

        // Add career position
        careerPointCreator.addCareerPosition();
        careerPositionCreator = new CareerPositionCreator("Engineer", "9/1/2016", "10/5/2016", "Master Code monkey");
        careerPositionCreator.fillView();
        careerPositionCreator.saveCareerPosition();

        // Add career position
        careerPointCreator.addCareerPosition();
        careerPositionCreator = new CareerPositionCreator("Top Dawg", "10/6/2016", "11/8/2017", "Changed lives");
        careerPositionCreator.fillView();
        careerPositionCreator.saveCareerPosition();

        // Save career point
        careerPointCreator.saveCareerPoint();


        onView(withId(R.id.add_career_point_button)).perform(click());

        // Individual Career Point screen
        careerPointCreator = new CareerPointCreator("Nintendo", "Japan", "11/10/2017", "12/5/2017");
        careerPointCreator.fillView();

        // Add career position
        careerPointCreator.addCareerPosition();
        careerPositionCreator = new CareerPositionCreator("Apprentice", "11/10/2017", "12/5/2017", "Learned Donkey Kong");
        careerPositionCreator.fillView();
        careerPositionCreator.saveCareerPosition();

        // Save career point
        careerPointCreator.saveCareerPoint();

        // Submit career
        onView(withId(R.id.submit_button)).perform(click());
    }

}
