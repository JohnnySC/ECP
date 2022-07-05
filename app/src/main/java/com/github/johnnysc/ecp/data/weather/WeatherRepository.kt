package com.github.johnnysc.ecp.data.weather

interface WeatherRepository {

    suspend fun isDefaultCitySet(): Boolean

    suspend fun weatherInDefaultCity(): String
}