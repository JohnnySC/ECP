package com.github.johnnysc.ecp.data.weather.local

import com.github.johnnysc.ecp.data.weather.CityData
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoDefaultCityException

interface CityLocalDataSource {

    fun getDefaultCity(): CityData

    fun saveDefaultCity(newDefCity: String): CityData

    class Base(private val preferenceDataStore: CityPreferenceDataStore) : CityLocalDataSource {

        override fun getDefaultCity(): CityData {
            val cityName = preferenceDataStore.read(DEF_CITY_KEY)
            if (cityName.isEmpty()) {
                throw ThereIsNoDefaultCityException()
            }
            return CityData.Base(cityName)
        }

        override fun saveDefaultCity(newDefCity: String): CityData {
            preferenceDataStore.save(DEF_CITY_KEY, newDefCity)
            return CityData.Base(newDefCity)
        }

        companion object {

            private const val DEF_CITY_KEY = "def city key"
        }
    }
}