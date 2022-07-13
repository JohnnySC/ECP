package com.github.johnnysc.ecp.data.weather.cache

import android.content.SharedPreferences

interface CityPreferenceDataStore {

    fun save(key: String, data: String)

    fun read(key: String): String

    class Base(private val sharedPreferences: SharedPreferences) : CityPreferenceDataStore {

        override fun save(key: String, data: String) = sharedPreferences.edit().putString(key, data).apply()

        override fun read(key: String): String = sharedPreferences.getString(key, null) ?: ""
    }
}