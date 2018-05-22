package com.marshmallow.beehive;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.marshmallow.beehive.models.CareerPositionModel;
import com.marshmallow.beehive.models.ModelManager;
import com.marshmallow.beehive.ui.profileSetup.ProfileSetupCareerPositionActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by George on 5/21/2018.
 */
@RunWith(AndroidJUnit4.class)
public class ProfileSetupCareerPositionTest {

    @Rule
    public ActivityTestRule<ProfileSetupCareerPositionActivity> profileSetupCareerPositionActivityActivityTestRule = new ActivityTestRule<>(ProfileSetupCareerPositionActivity.class);

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Test
    public void testFillOutCareerPointPosition() {
        CareerPositionCreator careerPositionCreator = new CareerPositionCreator("Engineer", "5/3/2019", "4/21/2020", "Code warrior");

        // Try to save without filling in all the fields
        onView(withId(R.id.career_position_text)).perform(closeSoftKeyboard());
        onView(withId(R.id.save_button)).perform(click());
        onView(withId(R.id.career_position_text)).check(matches(hasErrorText("Career position title cannot be empty")));
        careerPositionCreator.fillPositionText();
        onView(withId(R.id.career_position_text)).perform(closeSoftKeyboard());
        onView(withId(R.id.save_button)).perform(click());
        onView(withId(R.id.start_date_text)).check(matches(hasErrorText("Start date cannot be empty")));
        careerPositionCreator.fillStartDate();
        onView(withId(R.id.start_date_text)).perform(closeSoftKeyboard());
        onView(withId(R.id.save_button)).perform(click());
        onView(withId(R.id.end_date_text)).check(matches(hasErrorText("End date cannot be empty")));
        careerPositionCreator.fillEndDate();
        careerPositionCreator.fillSummary();

        careerPositionCreator.saveCareerPosition();
        CareerPositionModel careerPositionModel = ModelManager.getInstance().getActiveCareerPointModel().getCareerPositionModels().get(0);
        assertEquals(careerPositionModel.getPositionName(), careerPositionCreator.title);
        assertEquals(careerPositionModel.getStartDate(), careerPositionCreator.start);
        assertEquals(careerPositionModel.getEndDate(), careerPositionCreator.end);
        assertEquals(careerPositionModel.getSummary(), careerPositionCreator.summary);
    }
}
