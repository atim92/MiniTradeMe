package com.tatam.minitrademe.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.tatam.minitrademe.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Test
    fun navigationMenu_openListingFragment() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.bottom_navigation_view)).perform(click())
    }

    @Test
    fun test_press_on_cart_icon_toast_isdisplayed() {
        onView(withId(R.id.action_cart))
            .inRoot(ToastMatcher().apply {
                matches(isDisplayed())
            });
    }

}