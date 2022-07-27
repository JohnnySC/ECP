package com.github.johnnysc.ecp.presentation.main

import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.ecp.core.BaseViewsActions
import com.github.johnnysc.ecp.core.InternetConnection
import com.github.johnnysc.ecp.sl.ProvideSharedPreferences
import com.github.johnnysc.ecp.sl.weather.ProvideCitySharedPref
import org.junit.Before

abstract class MainActivityWeatherTest : BaseViewsActions() {

    protected lateinit var internetConnection: InternetConnection.Write

    @Before
    fun setUp() {
        val coreModule = CoreModule.Base(appContext)
        ProvideCitySharedPref(coreModule).provideSharedPreferences().edit().clear()
            .apply()
        internetConnection = InternetConnection.Base(
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