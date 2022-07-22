package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.coremvvm.data.MakeService
import com.github.johnnysc.coremvvm.data.ProvideRetrofitBuilder
import com.github.johnnysc.ecp.data.weather.cloud.WeatherService

interface ProvideWeatherService {

    fun provideWeatherService(): WeatherService

    class Base(
        retrofitBuilder: ProvideRetrofitBuilder
    ) : MakeService.Abstract(
        retrofitBuilder
    ), ProvideWeatherService {

        override fun provideWeatherService(): WeatherService = service(WeatherService::class.java)

        override fun baseUrl(): String = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/"
    }
}