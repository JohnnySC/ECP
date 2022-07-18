package com.github.johnnysc.ecp.data.weather.cache

import android.util.Log
import com.github.johnnysc.ecp.data.weather.CityData
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoDefaultCityException

private const val TAG = "CityCacheDataSource"
interface CityCacheDataSource {

    fun getDefaultCity(): CityData

    fun saveDefaultCity(newDefaultCity: String)

    class Base(private val preferenceDataStore: CityPreferenceDataStore) : CityCacheDataSource {

        override fun getDefaultCity(): CityData {
            val cityName = preferenceDataStore.read(DEFAULT_CITY_KEY)
            Log.d(TAG, "getDefaultCity: ${cityName}")
            if (cityName.isEmpty()) {
                throw ThereIsNoDefaultCityException()
            }
            return CityData.Base(cityName)
        }

        override fun saveDefaultCity(newDefaultCity: String) {
            preferenceDataStore.save(DEFAULT_CITY_KEY, newDefaultCity)
        }

        companion object {

            private const val DEFAULT_CITY_KEY = "default city key"
        }
    }
}