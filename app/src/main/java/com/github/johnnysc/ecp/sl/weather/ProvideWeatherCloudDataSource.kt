package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.ecp.core.Converter
import com.github.johnnysc.ecp.core.ReadRawResource
import com.github.johnnysc.ecp.data.weather.cloud.HandleWeatherExceptions
import com.github.johnnysc.ecp.data.weather.cloud.WeatherCloudDataSource
import com.github.johnnysc.ecp.sl.ProvideSharedPreferences
import com.google.gson.Gson

interface ProvideWeatherCloudDataSource {

    fun provideCloudDataSource(): WeatherCloudDataSource

    class Base(private val provideWeatherCloud: ProvideWeatherCloud) :
        ProvideWeatherCloudDataSource {

        override fun provideCloudDataSource(): WeatherCloudDataSource {
            return WeatherCloudDataSource.Base(
                HandleWeatherExceptions(),
                provideWeatherCloud.provideWeatherCloud()
            )
        }
    }

    class Mock(
        private val gson: Gson,
        private val readRawResource: ReadRawResource,
        private val provideSharedPreferences: ProvideSharedPreferences
    ) :
        ProvideWeatherCloudDataSource {

        override fun provideCloudDataSource() = WeatherCloudDataSource.Mock(
            WeatherCloudDataSource.Mock.FetchWeather.Base(
                Converter.Base(
                    WeatherCloudDataSource.Mock.FetchWeather.WeatherResponseToken(),
                    gson
                ), readRawResource
            ), WeatherCloudDataSource.InternetConnection.Base(provideSharedPreferences.provideSharedPreferences()),
            HandleWeatherExceptions()
        )
    }
}