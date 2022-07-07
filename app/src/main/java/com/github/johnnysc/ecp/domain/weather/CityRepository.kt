package com.github.johnnysc.ecp.domain.weather

import com.github.johnnysc.ecp.data.weather.CityData
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoCityWithSuchTitle
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoConnection
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoDefaultCity

interface CityRepository {
    @Throws(ThereIsNoDefaultCity::class)
    suspend fun getDefaultCity(): CityData

    @Throws(ThereIsNoConnection::class, ThereIsNoCityWithSuchTitle::class)
    suspend fun getCityCoordinatesByName(cityName: String): CityData

    suspend fun saveDefaultCity(cityDomain: CityDomain)

}