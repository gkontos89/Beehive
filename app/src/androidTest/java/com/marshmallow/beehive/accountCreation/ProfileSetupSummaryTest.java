package com.marshmallow.beehive.accountCreation;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.RecyclerViewMatcher;
import com.marshmallow.beehive.models.ModelManager;
import com.marshmallow.beehive.ui.profileSetup.ProfileSetupSummaryActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by George on 5/20/2018.
 */

@RunWith(AndroidJUnit4.class)
public class ProfileSetupSummaryTest {
    @Rule
    public ActivityTestRule<ProfileSetupSummaryActivity> profileSetupSummaryActivityActivityTestRule = new ActivityTestRule<>(ProfileSetupSummaryActivity.class);

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Test
    public void testFillInProfileSummary() {
        // Test empty field errors
        onView(ViewMatchers.withId(R.id.summary_text_entry)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.next_button)).perform(click());
        onView(withId(R.id.summary_text_entry)).check(matches(hasErrorText("Summary text cannot be empty")));
        onView(withId(R.id.summary_text_entry)).perform(typeText("Looking for adventure"), ViewActions.closeSoftKeyboard());

        // Validate pursuit additions
        onView(withId(R.id.pursuit_text_entry)).perform(typeText("Hiking"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.add_pursuit_button)).perform(click());
        onView(withId(R.id.pursuit_text_entry)).perform(typeText("Boating"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.add_pursuit_button)).perform(click());
        onView(withId(R.id.pursuit_text_entry)).perform(typeText("Parasailing"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.add_pursuit_button)).perform(click());
        assertEquals(ModelManager.getInstance().getUserModel().getUserStory().getPursuits().size(), 3);
        onView(withRecyclerView(R.id.pursuits_list).atPosition(0)).check(matches(hasDescendant(withText("Hiking"))));
        onView(withRecyclerView(R.id.pursuits_list).atPosition(1)).check(matches(hasDescendant(withText("Boating"))));

        // Validate empty pursuits
        onView(withId(R.id.summary_text_entry)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.add_pursuit_button)).perform(click());
        onView(withId(R.id.pursuit_text_entry)).check(matches(hasErrorText("Pursuit cannot be empty")));

        // Remove pursuit
        onView(withId(R.id.pursuits_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new ViewAction() {
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

        assertEquals(ModelManager.getInstance().getUserModel().getUserStory().getPursuits().size(), 2);
        onView(withId(R.id.pursuits_list)).check(matches(not(hasDescendant(withText("Hiking")))));


        // TODO story questions
        // Save data and validate model storage
        onView(withId(R.id.next_button)).perform(click());
        assertEquals(ModelManager.getInstance().getUserModel().getUserStory().getSummary(), "Looking for adventure");
        assertEquals(ModelManager.getInstance().getUserModel().getUserStory().getPursuits().size(), 2);

        // Move back and validate data persisted
        onView(withId(R.id.back_button)).perform(click());
        onView(withId(R.id.summary_text_entry)).check(matches(withText("Looking for adventure")));
        onView(withRecyclerView(R.id.pursuits_list).atPosition(0)).check(matches(hasDescendant(withText("Boating"))));
        onView(withRecyclerView(R.id.pursuits_list).atPosition(1)).check(matches(hasDescendant(withText("Parasailing"))));
    }
}
