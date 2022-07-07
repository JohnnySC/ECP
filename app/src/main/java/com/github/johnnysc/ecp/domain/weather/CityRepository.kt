package com.github.johnnysc.ecp.domain.weather

import com.github.johnnysc.ecp.data.weather.CityData
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoCityWithSuchTitle
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoConnection
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoDefaultCity

interface CityRepository {
    @Throws(ThereIsNoDefaultCity::class)
    fun getDefaultCity(): CityData

    @Throws(ThereIsNoConnection::class,ThereIsNoCityWithSuchTitle::class)
    fun getCityCoordinatesByName(cityName:String):CityData

    fun saveDefaultCity(cityDomain: CityDomain)

}