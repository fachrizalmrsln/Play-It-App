package com.id.zul.playit.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.id.zul.playit.R
import com.id.zul.playit.utils.IdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get: Rule
    var mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(IdlingResource.myIdle)
    }

    @Test
    fun testFragmentBehaviour() {
        onView(withId(R.id.nav_movie))
            .check(matches(isDisplayed()))

        onView(withId(R.id.nav_tv))
            .check(matches(isDisplayed()))

        onView(withId(R.id.nav_tv))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.nav_movie))
            .check(matches(isDisplayed()))
            .perform(click())

    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.myIdle)
    }

}