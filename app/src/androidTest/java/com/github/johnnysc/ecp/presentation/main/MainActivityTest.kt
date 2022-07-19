package com.github.johnnysc.ecp.presentation.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences


import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches

import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.core.RecyclerViewMatcher
import com.github.johnnysc.ecp.core.lazyActivityScenarioRule

import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.data.weather.cloud.WeatherCloudDataSource
import com.github.johnnysc.ecp.sl.MainApplication
import com.github.johnnysc.ecp.sl.weather.ProvideWeatherCloudDataSource
import org.junit.After

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
abstract class MainActivityTest {
    @get:Rule
    val activityTestRule = lazyActivityScenarioRule<MainActivity>(launchActivity = false)
    private lateinit var resources: ManageResources
    private lateinit var appContext: Context
    private val citySharedPrefsKey = "city_prefs"
    private val testSettingsSharedPrefName="test_settings"
    protected lateinit var internetConnection: WeatherCloudDataSource.InternetConnection.Write
    @Before
    fun setUp() {
        appContext = ApplicationProvider.getApplicationContext()
        resources = ManageResources.Base(appContext)
        activityTestRule.launch(Intent(appContext, MainActivity::class.java))
        appContext.getSharedPreferences(citySharedPrefsKey,Context.MODE_PRIVATE).edit().clear().apply()
        internetConnection=WeatherCloudDataSource.InternetConnection.Base(appContext.getSharedPreferences(testSettingsSharedPrefName,Context.MODE_PRIVATE))
    }

    protected fun checkItemText(position: Int, text: String) {
        onView(RecyclerViewMatcher(R.id.messagesRecyclerView).atPosition(position)).check(
            matches(withText(text))
        )
    }

    protected fun Int.createRequestForDefaultCitySet(cityName: String) =
        "${resources.string(this)} $cityName"

    protected fun Int.createRequestForWeatherInCity(cityName: String) =
        "${resources.string(this)} $cityName"

    protected fun Int.createStringFromId() =
        resources.string(this)

    protected fun Int.createSuccessResponseForSetDefaultCity(cityName: String) =
        resources.string(this).format(cityName)

    protected fun Int.createSuccessResponseForTemperatureInCity(temperature: Float) =
        resources.string(this).format(temperature)
}