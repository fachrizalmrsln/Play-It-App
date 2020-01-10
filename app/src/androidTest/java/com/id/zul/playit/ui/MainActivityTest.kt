package com.id.zul.playit.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.id.zul.playit.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get: Rule
    var mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testFragmentBehaviour() {
        onView(withId(R.id.nav_movie))
            .check(matches(isDisplayed()))

        delay(2000)

        onView(withId(R.id.nav_tv))
            .check(matches(isDisplayed()))

        delay(2000)

        onView(withId(R.id.nav_tv))
            .check(matches(isDisplayed()))
            .perform(click())

        delay(3000)

        onView(withId(R.id.nav_movie))
            .check(matches(isDisplayed()))
            .perform(click())

        delay(3000)

    }

    private fun delay(sec: Long) {
        try {
            Thread.sleep(sec)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}