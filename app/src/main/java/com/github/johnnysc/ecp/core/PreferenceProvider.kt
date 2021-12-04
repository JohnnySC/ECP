package com.github.johnnysc.ecp.core

import android.content.SharedPreferences

interface PreferenceProvider {
    fun provideSharedPreferences(name: String): SharedPreferences
}