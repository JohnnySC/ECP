package com.github.johnnysc.ecp.data.core

import android.content.SharedPreferences

interface PreferenceDataSource : IntStorage, StringStorage {
    class Base(private val sharedPreferences: SharedPreferences) : PreferenceDataSource {

        override fun save(key: String, value: Int) =
            sharedPreferences.edit().putInt(key, value).apply()

        override fun save(key: String, value: String) =
            sharedPreferences.edit().putString(key, value).apply()

        override fun read(key: String, defaultValue: Int): Int =
            sharedPreferences.getInt(key, defaultValue)

        override fun read(key: String, defaultValue: String): String =
            sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }
}