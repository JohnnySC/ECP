package com.github.johnnysc.ecp.data.weather.remote

import com.github.johnnysc.coremvvm.data.MakeService
import com.github.johnnysc.coremvvm.data.ProvideRetrofitBuilder

interface ProvideWeatherService {

    fun getService(): WeatherService

    class Base(
        retrofitBuilder: ProvideRetrofitBuilder
    ) : MakeService.Abstract(
        retrofitBuilder
    ), ProvideWeatherService {

        override fun getService(): WeatherService = service(WeatherService::class.java)

        override fun baseUrl(): String = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/"
    }
}