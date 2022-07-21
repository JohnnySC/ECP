package com.github.johnnysc.ecp.presentation.main


import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.ecp.core.BaseViewsActions
import com.github.johnnysc.ecp.data.weather.cloud.WeatherCloudDataSource
import com.github.johnnysc.ecp.sl.ProvideSharedPreferences
import com.github.johnnysc.ecp.sl.weather.ProvideCitySharedPref
import org.junit.Before
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
abstract class MainActivityWeatherTest : BaseViewsActions() {

    protected lateinit var internetConnection: WeatherCloudDataSource.InternetConnection.Write

    @Before
    fun setUp() {
        val coreModule = CoreModule.Base(appContext)
        ProvideCitySharedPref(coreModule).provideSharedPreferences().edit().clear()
            .apply()
        internetConnection = WeatherCloudDataSource.InternetConnection.Base(
            ProvideSharedPreferences.ProvideTestSettingsSharedPref(coreModule)
                .provideSharedPreferences()
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