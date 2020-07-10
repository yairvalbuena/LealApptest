package yairvalbuena.android.lealtestapp;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestFuncionalidad {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testFuncionalidad() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerViewA),
                        childAtPosition(
                                withId(R.id.navigationview),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.FAB),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.drawerlayout),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.design_navigation_view),
                        childAtPosition(
                                allOf(withId(R.id.navigationview),
                                        childAtPosition(
                                                withId(R.id.drawerlayout),
                                                0)),
                                1),
                        isDisplayed()));
        recyclerView2.check(doesNotExist());

        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("Más opciones"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                0),
                        isDisplayed()));
        overflowMenuButton.perform(click());

        ViewInteraction overflowMenuButton2 = onView(
                allOf(withContentDescription("Más opciones"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                0),
                        isDisplayed()));
        overflowMenuButton2.perform(click());

        ViewInteraction overflowMenuButton3 = onView(
                allOf(withContentDescription("Más opciones"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                0),
                        isDisplayed()));
        overflowMenuButton3.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Reload"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.design_navigation_view),
                        childAtPosition(
                                allOf(withId(R.id.navigationview),
                                        childAtPosition(
                                                withId(R.id.drawerlayout),
                                                0)),
                                0),
                        isDisplayed()));
        recyclerView3.check(matches(isDisplayed()));

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.design_navigation_view),
                        childAtPosition(
                                allOf(withId(R.id.navigationview),
                                        childAtPosition(
                                                withId(R.id.drawerlayout),
                                                0)),
                                0),
                        isDisplayed()));
        recyclerView4.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
