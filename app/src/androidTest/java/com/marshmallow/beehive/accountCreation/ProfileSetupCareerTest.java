package com.marshmallow.beehive.accountCreation;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.ui.profileSetup.ProfileSetupCareerActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by George on 5/21/2018.
 */

@RunWith(AndroidJUnit4.class)
public class ProfileSetupCareerTest {

    @Rule
    public ActivityTestRule<ProfileSetupCareerActivity> profileSetupCareerActivityActivityTestRule = new ActivityTestRule<>(ProfileSetupCareerActivity.class);

    @Test
    public void testSetupCareer() {
        onView(ViewMatchers.withId(R.id.add_career_point_button)).perform(click());

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
