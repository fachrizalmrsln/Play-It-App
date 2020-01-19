package com.id.zul.playit.ui.favorite

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
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
class FavoriteFragmentTest {

    @get: Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(IdlingResource.myIdle)
    }

    @Test
    fun addMovieToFavorite() {

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

        onView(withId(R.id.btn_favorite))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(ViewMatchers.isRoot())
            .perform(ViewActions.pressBack())

        onView(withId(R.id.nav_favorite))
            .perform(click())

        onView(withId(R.id.rv_favorite_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        onView(withId(R.id.btn_favorite))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(ViewMatchers.isRoot())
            .perform(ViewActions.pressBack())

    }

    @Test
    fun addTvToFavorite() {

        onView(withId(R.id.nav_tv))
            .check(matches(ViewMatchers.isCompletelyDisplayed()))

        onView(withId(R.id.nav_tv))
            .perform(click())

        onView(withId(R.id.rv_tv_show))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rv_tv_show))
            .check(RecyclerViewItemCountAssertion(20))

        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))

        onView(withId(R.id.rv_tv_show))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    8,
                    click()
                )
            )

        onView(withId(R.id.btn_favorite))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(ViewMatchers.isRoot())
            .perform(ViewActions.pressBack())

        onView(withId(R.id.nav_favorite))
            .perform(click())

        onView(withId(R.id.vp_favorite))
            .check(matches(isDisplayed()))

        onView(withId(R.id.vp_favorite))
            .perform(swipeLeft())

        onView(withId(R.id.rv_favorite_tv))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        onView(withId(R.id.btn_favorite))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(ViewMatchers.isRoot())
            .perform(ViewActions.pressBack())

    }


    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.myIdle)
    }
}