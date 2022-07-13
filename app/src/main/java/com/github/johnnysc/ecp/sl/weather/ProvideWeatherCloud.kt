package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.coremvvm.data.MakeService
import com.github.johnnysc.coremvvm.data.ProvideRetrofitBuilder
import com.github.johnnysc.ecp.data.weather.cloud.WeatherCloud

interface ProvideWeatherCloud {

    fun provideWeatherCloud(): WeatherCloud

    class Base(
        retrofitBuilder: ProvideRetrofitBuilder
    ) : MakeService.Abstract(
        retrofitBuilder
    ), ProvideWeatherCloud {

        override fun provideWeatherCloud(): WeatherCloud = service(WeatherCloud::class.java)

        override fun baseUrl(): String = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/"
    }
}