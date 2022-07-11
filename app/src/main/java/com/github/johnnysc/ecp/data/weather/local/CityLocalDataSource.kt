package com.github.johnnysc.ecp.data.weather.local

import com.github.johnnysc.coremvvm.data.PreferenceDataStore
import com.github.johnnysc.ecp.data.weather.CityData
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoCityWithSuchTitleException
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoDefaultCityException
import com.github.johnnysc.ecp.domain.DomainException

interface CityLocalDataSource {
    fun getDefaultCity():CityData

    fun saveDefaultCity(newDefCity:String):CityData

    class Base(private val preferenceDataStore: CityPreferenceDataStore):CityLocalDataSource
    {
        private val DEF_CITY_KEY="default_city"
        override fun getDefaultCity(): CityData {
            val cityName=preferenceDataStore.read(DEF_CITY_KEY)
            return if(cityName.isEmpty())
            {
                throw ThereIsNoDefaultCityException()
            }
            else
                CityData.Base(cityName)
        }

        override fun saveDefaultCity(newDefCity: String): CityData {
            preferenceDataStore.save(DEF_CITY_KEY,newDefCity)
            return CityData.Base(newDefCity)
        }
    }


}