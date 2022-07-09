package com.github.johnnysc.ecp.domain.weather

import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoCityWithSuchTitle
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoConnection
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoDefaultCity

interface WeatherRepository {

    @Throws(ThereIsNoConnection::class, ThereIsNoCityWithSuchTitle::class)
    suspend fun getWeatherInCity(city: String): WeatherDomain

    @Throws(ThereIsNoDefaultCity::class, ThereIsNoConnection::class)
    suspend fun getWeatherInDefaultCity(): WeatherDomain

    @Throws(ThereIsNoCityWithSuchTitle::class, ThereIsNoConnection::class)
    suspend fun saveDefaultCity(newCity: String): CityDomain


}