package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.ecp.BuildConfig.USE_MOCKS
import com.github.johnnysc.ecp.data.weather.cache.CityPreferenceDataStore

interface ProvideCityPreferenceDataStore {

    fun provideCityPreferenceDataStore(): CityPreferenceDataStore

    class Base(private val coreModule: CoreModule) : ProvideCityPreferenceDataStore {
        private val citySharedPrefsKey = "city_prefs"
        override fun provideCityPreferenceDataStore()= CityPreferenceDataStore.Base(coreModule.sharedPreferences(citySharedPrefsKey))
    }
}