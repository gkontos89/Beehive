package com.marshmallow.beehive.accountCreation;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.RecyclerViewMatcher;
import com.marshmallow.beehive.models.CareerPointModel;
import com.marshmallow.beehive.models.ModelManager;
import com.marshmallow.beehive.ui.profileSetup.ProfileSetupCareerPointActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by George on 5/21/2018.
 */
@RunWith(AndroidJUnit4.class)
public class ProfileSetupCareerPointTest {

    @Rule
    public ActivityTestRule<ProfileSetupCareerPointActivity> profileSetupCareerPointActivityActivityTestRule = new ActivityTestRule<>(ProfileSetupCareerPointActivity.class);

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Test
    public void testAddCareerPointAndSave() {
        CareerPointCreator careerPointCreator = new CareerPointCreator("Caterpillar", "Peoria, IL", "6/5/2011", "7/3/2017");

        // Test out error messages
        onView(ViewMatchers.withId(R.id.career_point_text)).perform(closeSoftKeyboard());
        onView(withId(R.id.save_button)).perform(click());
        onView(withId(R.id.career_point_text)).check(matches(hasErrorText("Career Point title cannot be empty")));
        careerPointCreator.fillTitleText();
        onView(withId(R.id.career_point_text)).perform(closeSoftKeyboard());
        onView(withId(R.id.save_button)).perform(click());
        onView(withId(R.id.career_point_location_text)).check(matches(hasErrorText("Location cannot be empty")));
        careerPointCreator.fillLocationText();
        onView(withId(R.id.career_point_location_text)).perform(closeSoftKeyboard());
        onView(withId(R.id.save_button)).perform(click());
        onView(withId(R.id.start_date_text)).check(matches(hasErrorText("Start date cannot be empty")));
        careerPointCreator.fillStartDateText();
        onView(withId(R.id.start_date_text)).perform(closeSoftKeyboard());
        onView(withId(R.id.save_button)).perform(click());
        onView(withId(R.id.end_date_text)).check(matches(hasErrorText("End date cannot be empty")));
        careerPointCreator.fillEndDateText();

        // Add a couple career positions
        onView(withId(R.id.add_career_position_button)).perform(click());
        CareerPositionCreator careerPositionCreator = new CareerPositionCreator("Associate", "6/5/2011", "7/1/2012", "Code monkey");
        careerPositionCreator.fillView();
        careerPositionCreator.saveCareerPosition();

        // Validate career point view data has persisted
        onView(withId(R.id.career_point_text)).check(matches(withText("Caterpillar")));
        onView(withId(R.id.career_point_location_text)).check(matches(withText("Peoria, IL")));
        onView(withId(R.id.start_date_text)).check(matches(withText("6/5/2011")));
        onView(withId(R.id.end_date_text)).check(matches(withText("7/3/2017")));

        onView(withId(R.id.add_career_position_button)).perform(click());
        careerPositionCreator = new CareerPositionCreator("Senior", "7/2/2012", "8/15/2013", "Code gorilla");
        careerPositionCreator.fillView();
        careerPositionCreator.saveCareerPosition();

        // Validate the career positions show up in the view
        onView(withRecyclerView(R.id.career_positions_rv).atPosition(0)).check(matches(hasDescendant(withText("Associate"))));
        onView(withRecyclerView(R.id.career_positions_rv).atPosition(1)).check(matches(hasDescendant(withText("Senior"))));

        // Remove career position
        onView(withId(R.id.career_positions_rv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public void perform(UiController uiController, View view) {
                View button = view.findViewById(R.id.remove_button);
                button.performClick();
            }
        }));
        onView(withId(R.id.career_positions_rv)).check(matches(not(hasDescendant(withText("Associate")))));
        onView(withId(R.id.save_button)).perform(click());

        // Validate model
        CareerPointModel careerPointModel = ModelManager.getInstance().getUserModel().getUserStory().getCareerPointModels().get(0);
        assertEquals(careerPointModel.getName(), "Caterpillar");
        assertEquals(careerPointModel.getLocation(), "Peoria, IL");
        assertEquals(careerPointModel.getStartDate(), "6/5/2011");
        assertEquals(careerPointModel.getEndDate(), "7/3/2017");
    }
}
