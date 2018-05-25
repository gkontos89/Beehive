package com.marshmallow.beehive;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by George on 5/21/2018.
 */
public class CareerPointCreator {

    public String title;
    public String location;
    public String start;
    public String end;

    public CareerPointCreator(String title, String location, String start, String end) {
        this.title = title;
        this.location = location;
        this.start = start;
        this.end = end;
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    public void fillView() {
        fillTitleText();
        fillLocationText();
        fillStartDateText();
        fillEndDateText();
    }

    public void fillTitleText() {
        onView(withId(R.id.career_point_text)).perform(typeText(title), ViewActions.closeSoftKeyboard());
    }

    public void fillLocationText() {
        onView(withId(R.id.career_point_location_text)).perform(typeText(location), closeSoftKeyboard());
    }

    public void fillStartDateText() {
        onView(withId(R.id.start_date_text)).perform(typeText(start), closeSoftKeyboard());
    }

    public void fillEndDateText() {
        onView(withId(R.id.end_date_text)).perform(typeText(end), closeSoftKeyboard());
    }

    public void saveCareerPoint() {
        onView(withId(R.id.save_button)).perform(click());
    }

    public void cancelCareerPoint() {
        onView(withId(R.id.cancel_button)).perform(click());
    }

    public void addCareerPosition() {
        onView(withId(R.id.add_career_position_button)).perform(click());
    }

    public void selectCareerPosition(int position) {
        onView(withRecyclerView(R.id.career_positions_rv).atPosition(position)).perform(click());
    }

    public void removeCareerPosition(int position) {
        onView(withId(R.id.career_positions_rv)).perform(RecyclerViewActions.actionOnItemAtPosition(position, new ViewAction() {
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
