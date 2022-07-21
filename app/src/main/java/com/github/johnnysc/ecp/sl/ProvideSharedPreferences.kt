package com.github.johnnysc.ecp.sl

import android.content.SharedPreferences
import com.github.johnnysc.coremvvm.sl.CoreModule

interface ProvideSharedPreferences {
    fun provideSharedPreferences():SharedPreferences

    abstract class AbstractProvideSharedPreferences(private val coreModule: CoreModule):ProvideSharedPreferences
    {
        protected abstract val sharedPreferencesName:String

        override fun provideSharedPreferences(): SharedPreferences {
            return coreModule.sharedPreferences(sharedPreferencesName)
        }
    }

    class ProvideTestSettingsSharedPref(coreModule: CoreModule):AbstractProvideSharedPreferences(coreModule)
    {
        override val sharedPreferencesName="testSettingsSharedPreferences"
    }



}