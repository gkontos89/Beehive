package com.marshmallow.beehive;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

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

/**
 * Created by George on 5/20/2018.
 */

@RunWith(AndroidJUnit4.class)
public class ProfileSetupSummaryTest {
    @Rule
    public ActivityTestRule<ProfileSetupSummaryActivity> loginActivityActivityTestRule = new ActivityTestRule<>(ProfileSetupSummaryActivity.class);

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Test
    public void testFillInProfileSummary() {
        onView(withId(R.id.summary_text_entry)).perform(typeText("Looking for adventure"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.pursuit_text_entry)).perform(typeText("Hiking"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.add_pursuit_button)).perform(click());
        onView(withId(R.id.pursuit_text_entry)).perform(typeText("Boating"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.add_pursuit_button)).perform(click());

        // TODO validate recyler view items
        onView(withRecyclerView(R.id.pursuits_list).atPosition(0)).check(matches(hasDescendant(withText("Hiking"))));
        onView(withRecyclerView(R.id.pursuits_list).atPosition(1)).check(matches(hasDescendant(withText("Boating"))));

        // TODO story questions

        onView(withId(R.id.next_button)).perform(click());
        assertEquals(ModelManager.getInstance().getUserModel().getUserStory().getSummary(), "Looking for adventure");
        assertEquals(ModelManager.getInstance().getUserModel().getUserStory().getPursuits().size(), 2);
    }

    @Test
    public void testEmptySummaryFailure() {
        onView(withId(R.id.summary_text_entry)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.next_button)).perform(click());
        onView(withId(R.id.summary_text_entry)).check(matches(hasErrorText("Summary text cannot be empty")));
    }

    @Test
    public void testEmptyPursuitAndPassion() {
        onView(withId(R.id.summary_text_entry)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.next_button)).perform(click());
        onView(withId(R.id.add_pursuit_button)).perform(click());
        onView(withId(R.id.pursuit_text_entry)).check(matches(hasErrorText("Pursuit cannot be empty")));
    }

    @Test
    public void testEmptyStoryQuestions() {
        // TODO fill out
    }

    @Test
    public void testDataSavedOnBackPressed() {
        // Fill out summary page
        onView(withId(R.id.summary_text_entry)).perform(typeText("Looking for adventure"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.pursuit_text_entry)).perform(typeText("Hiking"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.add_pursuit_button)).perform(click());
        onView(withId(R.id.pursuit_text_entry)).perform(typeText("Boating"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.add_pursuit_button)).perform(click());

        // Press the back button
        onView(withId(R.id.back_button)).perform(click());

        // Confirm profile summary data was saved in the model manager
        assertEquals(ModelManager.getInstance().getUserModel().getUserStory().getSummary(), "Looking for adventure");
        assertEquals(ModelManager.getInstance().getUserModel().getUserStory().getPursuits().size(), 2);

        // Press next and confirm the summary data is loaded into the summary page and contained in the model manager
//        onView(withId(R.id.next_button)).perform(click());
//        onView(withId(R.id.summary_text_entry)).check(matches(withText("Looking for adventure")));
//        onView(withRecyclerView(R.id.pursuits_list).atPosition(0)).check(matches(hasDescendant(withText("Hiking"))));
//        onView(withRecyclerView(R.id.pursuits_list).atPosition(1)).check(matches(hasDescendant(withText("Boating"))));

    }

    @Test
    public void testDeletingPursuits() {
        // Add pursuits
        onView(withId(R.id.pursuit_text_entry)).perform(typeText("Hiking"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.add_pursuit_button)).perform(click());
        onView(withId(R.id.pursuit_text_entry)).perform(typeText("Boating"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.add_pursuit_button)).perform(click());
        onView(withId(R.id.pursuit_text_entry)).perform(typeText("Parasailing"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.add_pursuit_button)).perform(click());

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
    }
}
