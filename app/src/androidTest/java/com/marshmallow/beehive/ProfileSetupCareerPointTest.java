package com.marshmallow.beehive;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.marshmallow.beehive.models.CareerPointModel;
import com.marshmallow.beehive.models.ModelManager;
import com.marshmallow.beehive.ui.profileSetup.ProfileSetupCareerPointActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;

/**
 * Created by George on 5/21/2018.
 */
@RunWith(AndroidJUnit4.class)
public class ProfileSetupCareerPointTest {

    @Rule
    public ActivityTestRule<ProfileSetupCareerPointActivity> profileSetupCareerPointActivityActivityTestRule = new ActivityTestRule<>(ProfileSetupCareerPointActivity.class);

    @Test
    public void testAddCareerPointAndSave() {
        CareerPointCreator careerPointCreator = new CareerPointCreator("Caterpillar", "Peoria, IL", "6/5/2011", "7/3/2017");
        careerPointCreator.fillView();
        onView(withId(R.id.save_button)).perform(click());
        assertEquals(ModelManager.getInstance().getUserModel().getUserStory().getCareerPointModels().size(), 1);
        CareerPointModel careerPointModel = ModelManager.getInstance().getUserModel().getUserStory().getCareerPointModels().get(0);
        assertEquals(careerPointModel.getName(), "Caterpillar");
        assertEquals(careerPointModel.getLocation(), "Peoria, IL");
        assertEquals(careerPointModel.getStartDate(), "6/5/2011");
        assertEquals(careerPointModel.getEndDate(), "7/3/2017");
    }
}
