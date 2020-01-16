package com.id.zul.playit.ui.movie

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
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
class MovieFragmentTest {

    @get: Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(IdlingResource.myIdle)
    }

    @Test
    fun testSelectItemBehaviour() {

        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rv_movies))
            .check(RecyclerViewItemCountAssertion(20))

        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))

        onView(withId(R.id.rv_movies))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    8,
                    click()
                )
            )

        onView(isRoot())
            .perform(ViewActions.pressBack())

    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.myIdle)
    }

}