package com.github.johnnysc.ecp.data.weather.cache

import com.github.johnnysc.ecp.data.weather.CityData
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoDefaultCityException

interface CityCacheDataSource {

    fun getDefaultCity(): CityData

    fun saveDefaultCity(newDefaultCity: String): CityData

    class Base(private val preferenceDataStore: CityPreferenceDataStore) : CityCacheDataSource {

        override fun getDefaultCity(): CityData {
            val cityName = preferenceDataStore.read(DEFAULT_CITY_KEY)
            if (cityName.isEmpty()) {
                throw ThereIsNoDefaultCityException()
            }
            return CityData.Base(cityName)
        }

        override fun saveDefaultCity(newDefaultCity: String): CityData {
            preferenceDataStore.save(DEFAULT_CITY_KEY, newDefaultCity)
            return CityData.Base(newDefaultCity)
        }

        companion object {

            private const val DEFAULT_CITY_KEY = "default city key"
        }
    }
}