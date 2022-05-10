package com.tatam.minitrademe.ui.fragments

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.tatam.minitrademe.R
import com.tatam.minitrademe.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class LatestListingsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun checkIfListingsListIsDisplayed() {

        val navController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<LatestListingsFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        Espresso.onView(withId(R.id.rvLatestListings))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}