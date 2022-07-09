package com.github.johnnysc.ecp.domain.weather

import com.github.johnnysc.coremvvm.domain.NoInternetConnectionException
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoCityWithSuchTitleException
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoDefaultCityException

interface WeatherRepository {

    @Throws(NoInternetConnectionException::class, ThereIsNoCityWithSuchTitleException::class)
    suspend fun getWeatherInCity(city: String): WeatherDomain

    @Throws(ThereIsNoDefaultCityException::class, NoInternetConnectionException::class)
    suspend fun getWeatherInDefaultCity(): WeatherDomain

    @Throws(ThereIsNoCityWithSuchTitleException::class, NoInternetConnectionException::class)
    suspend fun saveDefaultCity(newCity: String): CityDomain


}