package com.id.zul.playit.ui.tv

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.id.zul.playit.R
import com.id.zul.playit.ui.MainActivity
import com.id.zul.playit.utils.IdlingResource
import com.id.zul.playit.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TvFragmentTest {

    @get: Rule
    var activityTest = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(IdlingResource.myIdle)
    }

    @Test
    fun testSelectItemBehaviour() {

        onView(withId(R.id.nav_tv))
            .check(matches(isCompletelyDisplayed()))

        delay(2000)

        onView(withId(R.id.nav_tv))
            .perform(click())

        onView(withId(R.id.rv_tv_show))
            .check(RecyclerViewItemCountAssertion(20))

        delay(2000)

        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))

        delay(2000)

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

        delay(2000)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.myIdle)
    }

    // i'm keep using delay just for making sure the data show up properly before the other action
    private fun delay(sec: Long) {
        try {
            Thread.sleep(sec)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}