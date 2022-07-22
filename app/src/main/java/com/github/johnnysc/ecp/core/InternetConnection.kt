package com.github.johnnysc.ecp.core

import android.content.SharedPreferences

interface InternetConnection {

    interface Write : InternetConnection {

        fun turnOnInternet()
        fun turnOffInternet()
    }

    interface Read : InternetConnection {

        fun isInternetAvailable(): Boolean
    }

    interface Mutable : Write, Read

    class Base(private val sharedPreferences: SharedPreferences) : Mutable {

        companion object {
            private const val IS_INTERNET_AVAILABLE = "IS_INTERNET_AVAILABLE_KEY"
        }

        override fun turnOnInternet() {
            sharedPreferences.edit().putBoolean(IS_INTERNET_AVAILABLE, true).apply()
        }

        override fun turnOffInternet() {
            sharedPreferences.edit().putBoolean(IS_INTERNET_AVAILABLE, false).apply()
        }

        override fun isInternetAvailable() =
            sharedPreferences.getBoolean(IS_INTERNET_AVAILABLE, true)
    }
}