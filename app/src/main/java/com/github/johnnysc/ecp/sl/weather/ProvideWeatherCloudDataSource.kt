package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.ecp.core.InternetConnection
import com.github.johnnysc.ecp.data.weather.cloud.HandleWeatherExceptions
import com.github.johnnysc.ecp.data.weather.cloud.RemoteWeather
import com.github.johnnysc.ecp.data.weather.cloud.Weather
import com.github.johnnysc.ecp.data.weather.cloud.WeatherCloudDataSource
import com.github.johnnysc.ecp.sl.ProvideConvertRawResourceToPojoAdapter
import com.github.johnnysc.ecp.sl.ProvideSharedPreferences

interface ProvideWeatherCloudDataSource {

    fun provideCloudDataSource(): WeatherCloudDataSource

    class Base(private val provideWeatherCloud: ProvideWeatherService) :
        ProvideWeatherCloudDataSource {

        override fun provideCloudDataSource() = WeatherCloudDataSource.Base(
            HandleWeatherExceptions(),
            provideWeatherCloud.provideWeatherService()
        )

    }

    class Mock(
        private val provideConvertRawResourceToPojoAdapter: ProvideConvertRawResourceToPojoAdapter<Weather.Base, RemoteWeather>,
        private val provideSharedPreferences: ProvideSharedPreferences
    ) :
        ProvideWeatherCloudDataSource {

        override fun provideCloudDataSource() = WeatherCloudDataSource.Mock(
            provideConvertRawResourceToPojoAdapter.provideConvertRawResourceToPojoAdapter(),
            InternetConnection.Base(provideSharedPreferences.provideSharedPreferences()),
            HandleWeatherExceptions()
        )
    }
}