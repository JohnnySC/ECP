package com.github.johnnysc.ecp.domain.weather

import com.github.johnnysc.ecp.data.weather.WeatherData
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoConnection

interface WeatherRepository {

    @Throws(ThereIsNoConnection::class)
    suspend fun getWeatherInCity(cityDomain: CityDomain): WeatherData
}