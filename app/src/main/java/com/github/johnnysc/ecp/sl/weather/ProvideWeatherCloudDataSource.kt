package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.ecp.core.RawResourceReader
import com.github.johnnysc.ecp.data.weather.cloud.HandleExceptions
import com.github.johnnysc.ecp.data.weather.cloud.WeatherCloudDataSource
import com.google.gson.Gson

interface ProvideWeatherCloudDataSource {

    fun provideCloudDataSource(): WeatherCloudDataSource

    class Base(private val provideWeatherCloud: ProvideWeatherCloud) :
        ProvideWeatherCloudDataSource {

        override fun provideCloudDataSource(): WeatherCloudDataSource {
            return WeatherCloudDataSource.Base(
                HandleExceptions(),
                provideWeatherCloud.provideWeatherCloud()
            )
        }
    }

    class Mock(
        private val gson: Gson,
        private val rawResourceReader: RawResourceReader,
        private val coreModule: CoreModule
    ) :
        ProvideWeatherCloudDataSource {
        private val testSettingsSharedPrefName="test_settings"
        override fun provideCloudDataSource() = WeatherCloudDataSource.Mock(
            rawResourceReader,
            gson,
            WeatherCloudDataSource.InternetConnection.Base(coreModule.sharedPreferences(testSettingsSharedPrefName)),
            HandleExceptions ()
        )
    }
}