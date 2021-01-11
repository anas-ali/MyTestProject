package com.example.test.home.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.test.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeUiTests {

    //Due to lack of time I am unable to complete
    //mock server flow I am using Thread.sleep as an alternate for now

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checksShimmerLayoutVisibility() {
        onView(withId(R.id.shimmer_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun navigateToNextDetailScreen() {
        onView(withId(R.id.shimmer_layout)).check(matches(isDisplayed()))
        Thread.sleep(10000)

        onView(withText("Notebook")).check(matches(isDisplayed()))
        onView(withText("Notebook")).perform(click())

        Thread.sleep(10000)
        onView(withText("Detail")).check(matches(isDisplayed()))
    }

}
