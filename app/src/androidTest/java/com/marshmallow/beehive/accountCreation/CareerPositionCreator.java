package com.marshmallow.beehive.accountCreation;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;

import com.marshmallow.beehive.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by George on 5/21/2018.
 */
public class CareerPositionCreator {
    public String title;
    public String start;
    public String end;
    public String summary;

    public CareerPositionCreator(String title, String start, String end, String summary) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.summary = summary;
    }

    public void fillView() {
        fillPositionText();
        fillStartDate();
        fillEndDate();
        fillSummary();
    }

    public void fillPositionText() {
        onView(ViewMatchers.withId(R.id.career_position_text)).perform(typeText(title), ViewActions.closeSoftKeyboard());
    }

    public void fillStartDate() {
        onView(withId(R.id.start_date_text)).perform(typeText(start), closeSoftKeyboard());
    }

    public void fillEndDate() {
        onView(withId(R.id.end_date_text)).perform(typeText(end), closeSoftKeyboard());
    }

    public void fillSummary() {
        onView(withId(R.id.career_position_summary)).perform(typeText(summary), closeSoftKeyboard());
    }

    public void saveCareerPosition() {
        onView(withId(R.id.save_button)).perform(click());
    }

    public void cancelCareerPoint() {
        onView(withId(R.id.cancel_button)).perform(click());
    }

}
