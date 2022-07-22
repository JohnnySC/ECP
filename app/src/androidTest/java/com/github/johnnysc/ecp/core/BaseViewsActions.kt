package com.github.johnnysc.ecp.core

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.presentation.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
open class BaseViewsActions {

    @get:Rule
    val activityTestRule = lazyActivityScenarioRule<MainActivity>(launchActivity = false)
    protected lateinit var resources: ManageResources
    protected lateinit var appContext: Context

    @Before
    fun before() {
        appContext = ApplicationProvider.getApplicationContext()
        resources = ManageResources.Base(appContext)
        activityTestRule.launch(Intent(appContext, MainActivity::class.java))
    }

    protected fun Int.typeTextToEditText(input: String) {
        onView(withId(this)).perform(ViewActions.typeText(input))
    }

    protected fun Int.handleClick() {
        onView(withId(this)).perform(ViewActions.click())
    }

    protected fun checkItemText(position: Int, text: String) {
        onView(RecyclerViewMatcher(R.id.messagesRecyclerView).atPosition(position)).check(
            ViewAssertions.matches(ViewMatchers.withText(text))
        )
    }
}