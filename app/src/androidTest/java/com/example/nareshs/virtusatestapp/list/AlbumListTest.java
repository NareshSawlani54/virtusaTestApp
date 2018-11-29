package com.example.nareshs.virtusatestapp.list;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AlertDialog;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.matcher.RootMatchers.*;
import static org.hamcrest.Matchers.*;
import com.example.nareshs.virtusatestapp.R;
import com.example.nareshs.virtusatestapp.view.AlbumListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by naresh.s on 29-Nov-18.
 */
@RunWith(AndroidJUnit4.class)
public class AlbumListTest {

    @Rule
    public ActivityTestRule<AlbumListActivity> mActivityTestRule = new ActivityTestRule<>(AlbumListActivity.class);

    @Test
    public void checkListScroll() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            onView(allOf(withText("No Internet connection"),isDisplayed())).check(ViewAssertions.matches(isDisplayed()));
            onView(allOf(withText("Ok"),isDisplayed())).perform(click());
        } catch (NoMatchingViewException matex) {
            onView(allOf(withId(R.id.albumlist), isDisplayed()));
        }
    }

}
