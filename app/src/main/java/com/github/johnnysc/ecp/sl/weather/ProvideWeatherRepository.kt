package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.ecp.data.weather.BaseWeatherRepository
import com.github.johnnysc.ecp.domain.weather.WeatherRepository

interface ProvideWeatherRepository {
    fun provideWeatherRepository():WeatherRepository

    class Base(private val provideWeatherCloud: ProvideWeatherCloud):ProvideWeatherRepository {
        override fun provideWeatherRepository()=BaseWeatherRepository(provideWeatherCloud.provideWeatherCloud(),)


    }
    }


