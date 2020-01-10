package com.id.zul.playit.ui.tv.show

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.id.zul.playit.R
import com.id.zul.playit.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TvShowFragmentTest {

    @get: Rule
    var activityTest = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testScrollAndClickItem() {
        onView(withId(R.id.nav_tv))
            .check(matches(isCompletelyDisplayed()))

        delay(2000)

        onView(withId(R.id.nav_tv))
            .perform(click())

        delay(1000)

        onView(withId(R.id.rv_tv_show))
            .check(matches(isCompletelyDisplayed()))

        delay(3000)

        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))

        delay(3000)

        onView(withId(R.id.rv_tv_show))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    8,
                    click()
                )
            )

        delay(3000)

        onView(isRoot())
            .perform(pressBack())

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