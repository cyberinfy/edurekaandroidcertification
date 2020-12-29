package com.tcs.assignmentten;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTesting {
    private LoginPresenter loginPresenter;

    private LoginCredentials loginCredentials;

    @Mock
    private LoginRepositoryImpl loginRepository;

    @Mock
    private Contract.LoginView loginView;

    @Captor
    private ArgumentCaptor<LoginRepository.LoginListener> loginListenerArgumentCaptor;

    private String validUsername;
    private String validPassword;
    private String inValidUsername;
    private String inValidPassword;
    private final String expectedSuccessfulMessage = "Login Successful";
    private final String expectedFailedMessage = "Login Failed";
    private final int expectedSuccessfulColor = R.color.green;
    private final int expectedFailedColor = R.color.red;

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void initValidCredentials() {
        // Specify a valid username
        validUsername = "edureka";
        validPassword = "edureka123";
        inValidUsername = "dfgdfbhjk";
        inValidPassword = "ed";
        MockitoAnnotations.initMocks(this);
        loginPresenter = new LoginPresenter(loginRepository, loginView);
    }

    @Test
    public void loginTest() {

        //Checking if loginCredentials are passed as expected from loginPresenter to loginRepository<LoginRepositoryImpl>
        loginPresenter.login(loginCredentials = new LoginCredentials(validUsername,validPassword));
        verify(loginRepository).login(eq(loginCredentials),
                loginListenerArgumentCaptor.capture());

        //testing successful scenario and failure scenario
        testLoginScenario(expectedSuccessfulMessage, expectedSuccessfulColor);
        testLoginScenario(expectedFailedMessage, expectedFailedColor);

        //testing login flow with invalid credentials
        performLogin(inValidUsername, inValidPassword, expectedFailedMessage, expectedFailedColor);

        //testing login flow with invalid username
        performLogin(inValidUsername,validPassword, expectedFailedMessage, expectedFailedColor);

        //testing login flow with invalid password
        performLogin(validUsername, inValidPassword, expectedFailedMessage, expectedFailedColor);

        //testing login flow with valid credentials
        performLogin(validUsername,validPassword, expectedSuccessfulMessage, expectedSuccessfulColor);
    }

    private void testLoginScenario(String expectedMessage, int expectedColor) {
        //Defining ArgumentCaptor for verifying the success and failure cases
        ArgumentCaptor<String> message = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> colorID = ArgumentCaptor.forClass(Integer.class);

        //Checking if expectedMessage and colorID are passed as expected from loginListener<LoginPresenter> to loginView
        loginListenerArgumentCaptor.getValue().onLogin(expectedMessage,expectedColor);
        verify(loginView, Mockito.atLeastOnce()).onLogin(message.capture(), colorID.capture());
        assertEquals(expectedMessage, message.getValue());
        assertEquals(String.valueOf(expectedColor), colorID.getValue().toString());
    }

    private void performLogin(String usernameToBetyped, String passwordToBetyped, String expectedMessage, int expectedColor) {

        // Providing input and checking editTextUsername
        onView(withId(R.id.editTextTextUsername))
                .perform(clearText())
                .perform(typeText(usernameToBetyped), closeSoftKeyboard())
                .check(matches(withText(usernameToBetyped)));

        // Providing input and checking editTextPassword
        onView(withId(R.id.editTextTextPassword))
                .perform(clearText())
                .perform(typeText(passwordToBetyped), closeSoftKeyboard())
                .check(matches(withText(passwordToBetyped)));

        //Checking if button click is working as expected
        onView(withId(R.id.button)).perform(click());


        // Check that the text was changed.
        onView(withId(R.id.textViewResult))
                .check(matches(withText(expectedMessage)));
        onView(withId(R.id.constraintLayout))
                .check(matches(withBackgroundColor(expectedColor)));
    }

    public static Matcher<View> withBackgroundColor(final int expectedResourceId) {
        return new BoundedMatcher<View, View>(View.class) {
            int actualColor;
            int expectedColor;
            String message;

            @Override
            protected boolean matchesSafely(View item) {
                if (item.getBackground() == null) {
                    //no background color
                    message = "failed";
                    return false;
                }
                Resources resources = item.getContext().getResources();
                expectedColor = ResourcesCompat.getColor(resources, expectedResourceId, null);

                try {
                    actualColor = ((ColorDrawable) item.getBackground()).getColor();
                }
                catch (Exception e) {
                    actualColor = ((GradientDrawable) item.getBackground()).getColor().getDefaultColor();
                }
                finally {
                    if (actualColor == expectedColor) {
                        //background color did match expected
                        message = "success";
                    }
                }
                return actualColor == expectedColor;
            }
            @Override
            public void describeTo(final Description description) {
                if (actualColor != 0) {
                    //background color didn't match expected
                    message = "failed";
                }
                description.appendText(message);
            }
        };
    }
}