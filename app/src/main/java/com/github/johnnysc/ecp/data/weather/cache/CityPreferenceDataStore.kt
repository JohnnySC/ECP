package com.github.johnnysc.ecp.data.weather.cache

import android.content.SharedPreferences

interface CityPreferenceDataStore {

    fun save(key: String, data: String)

    fun read(key: String): String

    class Base(private val sharedPreferences: SharedPreferences) : CityPreferenceDataStore {

        override fun save(key: String, data: String) = sharedPreferences.edit().putString(key, data).apply()

        override fun read(key: String): String = sharedPreferences.getString(key, null) ?: ""
    }

    class Mock(isDefaultCityAvailable:Boolean):CityPreferenceDataStore {
        private var defCity:String= if(isDefaultCityAvailable)
        {
            "Экибастуз"
        }
        else
            ""
        override fun save(key: String, data: String) {
            defCity=data
        }

        override fun read(key: String)=defCity

    }
}