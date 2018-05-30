package com.marshmallow.beehive.accountCreation;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.ui.login.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * SuccessfulAccountCreation
 * - Launch app
 * - Enter email and password
 * - Confirm successful login and welcome page shown
 *
 * FailedAccountCreation
 * - Launch app
 * - Enter credentials that have already been created
 * - Confirm failed login and create account displayed again
 *
 * CreateAccount without entering proper email credentials
 * - confirm error shows on screen
 *
 * CreateAccount attempt without entering password
 * - confirm error shows on screen
 *
 * Created by George on 5/19/2018.
 */

@RunWith(AndroidJUnit4.class)
public class CreateAccountTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void testCreateAccountWithCredentials() {
        // Enter in an email and password
        onView(ViewMatchers.withId(R.id.email_text)).perform(typeText("gkontos89@gmail.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password_text)).perform(typeText("password"), ViewActions.closeSoftKeyboard());

        // Click create account button
        onView(withId(R.id.create_account_button)).perform(click());
    }

    @Test
    public void testCreateAccountWithEmptyCredentials() {
        // Create account without entering in an email address
        onView(withId(R.id.create_account_button)).perform(click());
        onView(withId(R.id.email_text)).check(matches(hasErrorText("Email Address Required")));

        // Create account without entering a password
        onView(withId(R.id.email_text)).perform(typeText("gkontos89@gmail.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.create_account_button)).perform(click());
        onView(withId(R.id.password_text)).check(matches(hasErrorText("Empty Password Not Allowed")));
    }

}
