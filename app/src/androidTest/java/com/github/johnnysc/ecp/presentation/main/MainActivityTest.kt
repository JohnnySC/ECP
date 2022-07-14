package com.github.johnnysc.ecp.presentation.main

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.core.RecyclerViewMatcher
import com.github.johnnysc.ecp.core.lazyActivityScenarioRule
import org.junit.Assert.*
import com.github.johnnysc.ecp.R

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
abstract class MainActivityTest {
    @get:Rule
    val activityTestRule = lazyActivityScenarioRule<MainActivity>(launchActivity = false)

    protected lateinit var resources: ManageResources
    protected lateinit var appContext:Context

    @Before
    fun setUp() {
        appContext=ApplicationProvider.getApplicationContext()
        resources=ManageResources.Base(appContext)
    }

    protected fun checkItemText(position: Int, text: String) {
        onView(RecyclerViewMatcher(R.id.messagesRecyclerView).atPosition(position)).check(
            matches(
                withText(text)
            )
        )
    }

    protected fun String.checkExistenceOfItemView(position: Int) {
        checkItemText(position, this)
    }
}