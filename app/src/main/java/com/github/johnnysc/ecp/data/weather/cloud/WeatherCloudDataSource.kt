package com.github.johnnysc.ecp.data.weather.cloud

import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError

interface WeatherCloudDataSource {

    suspend fun getWeather(cityName: String): RemoteWeather

    class Base(
        handleError: HandleError,
        private val weatherCloud: WeatherCloud
    ) : WeatherCloudDataSource, CloudDataSource.Abstract(handleError) {

        override suspend fun getWeather(cityName: String): RemoteWeather = handle {
            RemoteWeather.Base(weatherCloud.getWeather(cityName, WEATHER_API_KEY, UNITS, INCLUDE))
        }

        companion object {
            private const val WEATHER_API_KEY = "PLACE YOUR API KEY HERE"

            private const val UNITS = "metric"

            private const val INCLUDE = "current"
        }
    }
}