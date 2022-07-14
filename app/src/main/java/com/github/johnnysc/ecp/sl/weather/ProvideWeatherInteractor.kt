package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.ecp.domain.weather.WeatherInteractor

interface ProvideWeatherInteractor {
    fun provideWeatherInteractor():WeatherInteractor
}