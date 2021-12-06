package com.github.johnnysc.ecp.core

import android.content.SharedPreferences

interface ProvidePreferences {
    fun providePreferences(name: String): SharedPreferences
}