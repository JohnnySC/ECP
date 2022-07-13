package com.github.johnnysc.ecp.data.weather.remote

import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.ecp.data.weather.remote.KeyStore.WEATHER_API_KEY

interface WeatherRemoteDataSource {

    suspend fun getWeather(cityName: String): RemoteWeather

    class Base(
        handleError: HandleError,
        private val weatherService: WeatherService
    ) : WeatherRemoteDataSource, CloudDataSource.Abstract(handleError) {

        override suspend fun getWeather(cityName: String): RemoteWeather = handle {
            RemoteWeather.Base(weatherService.getWeather(cityName, WEATHER_API_KEY))
        }
    }
}