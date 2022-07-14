package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.ecp.data.weather.cloud.WeatherCloudDataSource

interface ProvideWeatherCloudDataSource {
    fun provideCloudDataSource():WeatherCloudDataSource

    class Base(private val provideWeatherCloud: ProvideWeatherCloud):ProvideWeatherCloudDataSource
    {
        override fun provideCloudDataSource(): WeatherCloudDataSource {
            return WeatherCloudDataSource.Base()
        }
    }
}