package com.github.johnnysc.ecp.core

import android.content.SharedPreferences

interface PreferencesProvider {
    fun provideSharedPreferences(name: String): SharedPreferences
}